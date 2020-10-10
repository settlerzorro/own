package sgs.skills.trigger;

import sgs.card.AbstractCard;
import sgs.data.constant.Const_Game;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_HandCards;
import sgs.player.AbstractPlayer;
import sgs.player.impl.P_Trigger;
import sgs.service.ViewManagement;
import sgs.skills.LockingSkillIF;
import sgs.skills.SkillMultiIF;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * 郭嘉【遗计】【天妒】
 * 
 * @author user
 * 
 */
public class GuoJia_yiji_tiandu extends P_Trigger implements LockingSkillIF,SkillMultiIF {
	//损失获得的牌数
	private static final int NUMBER = 2;
	//AbstractPlayer player;
	//记录给出去的牌数
	int n ;
	//相关UI面板的引用
	Panel_Control pc;
	Panel_HandCards ph;

	//技能集合
	private static final String[] names = {"遗计","天妒"};
	
	public GuoJia_yiji_tiandu(AbstractPlayer p) {
		this.player = p;
	}

	/**
	 * 重写受伤触发
	 */
	@Override
	public void afterLoseHP(AbstractPlayer murderer) {
		super.afterLoseHP(murderer);
		System.out.println("触发！当前线程为：" + Thread.currentThread().getName());
		// 获得N张牌
		for (int i = 0; i < NUMBER; i++) {
			player.getAction().addOneCardFromList();
		}
		player.refreshView();
		//如果是AI
		if(player.getState().isAI()){
			//TODO
			return;
		}
		pc = (Panel_Control) player.getPanel();
		ph = pc.getHand();
		//锁住
		player.getProcess().setSkilling(true);
		//显示
		SwingUtilities.invokeLater(run);
		// 等待处理
		player.getState().setRes(0);
		n=0;
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				AbstractPlayer toP = ph.getTarget().getList().get(0);
				for (int i = 0; i < ph.getSelectedList().size(); i++) {
					ph.getSelectedList().get(i).getCard().passToPlayer(player,
							toP);
					n++;
				}
				toP.refreshView();
				// 如果都给了，则跳出循环
				if (n >= NUMBER) {
					ViewManagement.getInstance().getPrompt().clear();
					break;
				} else {
					player.getState().setRes(0);
					player.refreshView();
					SwingUtilities.invokeLater(run);
				}
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				ViewManagement.getInstance().getPrompt().clear();
				break;
			}
		}
		synchronized (player.getProcess()) {
			player.getState().setRes(0);
			player.getProcess().setSkilling(false);
			player.getProcess().notify();
		}
	}

	/**
	 * 重写判定后触发
	 * 获取判定牌
	 */
	@Override
	public void afterCheck(AbstractCard c, boolean result) {
		ViewManagement.getInstance().printMsg(player.getInfo().getName()+"获得了判定牌："+c.toString());
		player.getAction().addCardToHandCard(c);
	}

	@Override
	public String getName() {
		return "遗计";
	}
	
	private Runnable run = new Runnable() {

		@Override
		public void run() {
			// 显示给的牌
			pc.getHand().unableToUseCard();
			int size = pc.getHand().getCards().size();
			for (int i = 1; i <= NUMBER - n; i++) {
				pc.getHand().getCards().get(size - i).setEnableToUse(true);
				pc.getHand().setSelectLimit(NUMBER);
			}
			pc.getHand().enableToClick();
			ViewManagement.getInstance().getPrompt().show_Message("请选择目标传递，或者取消");
		}
	};

	@Override
	public List<String> getNameList() {
		return Arrays.asList(names);
	}

	
}

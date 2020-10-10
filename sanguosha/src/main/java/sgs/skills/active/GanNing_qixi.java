package sgs.skills.active;

import sgs.card.AbstractCard;
import sgs.card.changed.Virtual_GuoHeChaiQiao;
import sgs.data.constant.Const_Game;
import sgs.data.enums.Colors;
import sgs.gui.main.Panel_Control;
import sgs.gui.main.Panel_Player;
import sgs.player.AbstractPlayer;
import sgs.player.PlayerIF;
import sgs.skills.SkillIF;

import javax.swing.*;
import java.util.List;

/**
 * �������ܡ���Ϯ��
 * @author user
 *
 */
public class GanNing_qixi implements Runnable,SkillIF{
	AbstractPlayer player;
	public GanNing_qixi(AbstractPlayer p){
		this.player = p;
	}
	
	@Override
	public void run() {
		final Panel_Control pc = (Panel_Control) player.getPanel();
		//��ʾ���к�ɫ��
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				pc.getHand().unableToUseCard();
				pc.getHand().remindToUse(Colors.HEITAO,Colors.MEIHUA);
				pc.getHand().setSelectLimit(1);
				pc.getHand().enableToClick();
				pc.getHand().setTargetCheck(false);
				// ���� ���
				List<Panel_Player> list = pc.getHand().getMain().getPlayers();
				for (int i = 0; i < list.size(); i++) {
					Panel_Player pp = list.get(i);
					// ��������ƻ���װ����
					if (pp.getPlayer().getState().getCardList().isEmpty()
							&& pp.getPlayer().getState().getEquipment().isEmpty()) {
						pp.disableToUse();
						continue;
					}
					pp.enableToUse();
				}
			}
		});
		//�ȴ�ѡ��
		while(true){
			if(player.getState().getRes()==Const_Game.OK){
				AbstractCard c = pc.getHand().getSelectedList().get(0).getCard();
				//ԭ�е��ƶ���
				c.throwIt(player);
				//�³�һ��������Ӳ���
				new Virtual_GuoHeChaiQiao(c).use(player, pc.getHand().getTarget().getList().get(0));
				break;
			}
			if(player.getState().getRes()==Const_Game.CANCEL){
				player.refreshView();
				break;
			}
		}
		//����ڻغ��У��ͽ�غ���
		if(player.getStageNum()==PlayerIF.STAGE_USECARDS){
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
		}		
	}

	@Override
	public String getName() {
		return "��Ϯ";
	}

	@Override
	public boolean isEnableUse() {
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}

package sanguosha1.player.impl;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.DelayKitIF;
import sanguosha1.command.Command_ThrowCards;
import sanguosha1.command.Command_UseCard;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.Frame_Main;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.player.Player_ProcessIF;
import sanguosha1.service.AI.AIProcessService;
import sanguosha1.service.ModuleManagement;
import sanguosha1.service.ViewManagement;
import sanguosha1.skills.SkillIF;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װ��ҵ�6���غϵĶ���
 * 
 * @author user
 * 
 */
public class P_Process implements Player_ProcessIF {
	protected AbstractPlayer player;
	protected AIProcessService AIProcess;
	protected boolean isSkilling;
	protected boolean canUseCard = true;
	public P_Process(AbstractPlayer p) {
		this.player = p;
		AIProcess = new AIProcessService(player);
	}

	/**
	 * ����
	 */
	public void start() {
		init();
		stage_begin();
		stage_check();
		stage_addCards();
		stage_useCards();
		stage_throwCrads();
		stage_end();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pass();
	}
	//��ʼ��
	private void init(){
		player.setSkip(false);
		player.getState().setExtDamage(0);
		player.getState().setUsedSha(false);
		player.getState().getEquipment().initAll();
		// ���ܳ�ʼ��
		List<SkillIF> skills = player.getState().getSkill();
		if (!skills.isEmpty()) {
			for (int i = 0; i < skills.size(); i++) {
				skills.get(i).init();
			}
		}
	}
	// �غϿ�ʼ
	public void stage_begin() {
		player.setStageNum(PlayerIF.STAGE_BEGIN);
		ModuleManagement.getInstance().getBattle().clear();
		ViewManagement.getInstance().printMsg( player.getInfo().getName() + "��ʼ");
		player.refreshView();
	}

	// �ж��׶�
	public void stage_check() {
		player.setStageNum(PlayerIF.STAGE_CHECK);
		ViewManagement.getInstance().printMsg( player.getInfo().getName() + "�ж�");
		//��ȡ�ж��Ƽ���
		List<AbstractCard> list = new ArrayList<AbstractCard>();
		for (int i = 0; i <player.getState().getCheckedCardList().size(); i++) {
			list.add(player.getState().getCheckedCardList().get(i));
		}
		for (int i = 0; i < list.size(); i++) {
			DelayKitIF d =  (DelayKitIF) list.get(i);
			d.doKit();
			//System.out.println(player.toString()+","+player.getState().getCheckedCardList().get(i).toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// ���ƽ׶�
	public void stage_addCards() {
		player.setStageNum(PlayerIF.STAGE_ADDCARDS);
		ViewManagement.getInstance().printMsg( player.getInfo().getName() + "����");
		/*System.out.println(player.getState().getId().toString()
				+ player.getInfo().getName() + "����");*/
		//���ƶ���
		/*SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				PaintService.drawGetCards(player, 2);
			}
		});*/
		player.getAction().addOneCardFromList();
		player.getAction().addOneCardFromList();

		player.refreshView();
	}

	// ���ƽ׶�
	public void stage_useCards() {
		if(!canUseCard){
			canUseCard = true;
			return;
		}
		if (player.getState().isAI) {
			AIProcess.stage_useCards();
			return;
		}
		player.setStageNum(PlayerIF.STAGE_USECARDS);
		player.refreshView();
		ViewManagement.getInstance().printMsg( player.getInfo().getName() + "����");
		/*
		System.out.println(player.getState().getId().toString()
				+ player.getInfo().getName() + "����");*/
		while (!player.isSkip()) {
			// ������ȷ��ʱ��
			if (player.getState().getRes() == Const_Game.OK) {
				Panel_Control pc = (Panel_Control) player.getPanel();
				Panel_HandCards ph = pc.getHand();
				Thread t = new Thread(new Command_UseCard(ph), "�����߳�");
				t.start();
				player.getState().setRes(0);

			}
			// ����������ܣ���ͣ��ǰ�̣߳��ȼ���ʩ����ϣ��ټ���
			if (player.getState().getRes() == Const_Game.SKILL) {
				Thread t = new Thread((Runnable) player.getState().getSkill()
						.get(0));
				t.start();
				player.getState().setRes(0);
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// �����ж�
			if (isSkilling||player.getState().isRequest) {
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//��Ϸ����
			if(Frame_Main.isGameOver){
				//pass();
				break;
			}
		}
	}

	// ���ƽ׶�
	public void stage_throwCrads() {
		if (player.getState().isAI) {
			AIProcess.stage_throwCrads();
			return;
		}
		Panel_Control pc = (Panel_Control) player.getPanel();
		player.setStageNum(PlayerIF.STAGE_THROWCRADS);
		ViewManagement.getInstance().printMsg( player.getInfo().getName() + "����");
		/*System.out.println(player.getState().getId().toString()
				+ player.getInfo().getName() + "����");*/
		player.refreshView();
		// ����Ƿ���Ҫ����
		if (player.getState().getCardList().size() <= player.getState()
				.getCurHP())
			return;
		ViewManagement.getInstance().getPrompt().show_RemindToThrow(
				player.getState().getCardList().size()
						- player.getState().getCurHP());
		pc.getHand().setSelectLimit(
				player.getState().getCardList().size()
						- player.getState().getCurHP());
		while (true) {
			/*pc.getHand().setSelectLimit(
					player.getState().getCardList().size()
							- player.getState().getCurHP());*/
			if (player.getState().getRes() == Const_Game.OK) {

				new Thread(new Command_ThrowCards(pc.getHand())).start();
				/*MyThread t = ModuleManagement.getThreadUseCard();
				t.setTarget(new Command_ThrowCards(pc.getHand()));
				t.start();*/
				player.getState().setRes(0);
			}
			if(player.getState().getCardList().size() > player.getState()
					.getCurHP()){
				continue;
			}else{
				break;
			}
		}
		ViewManagement.getInstance().getPrompt().clear();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// �غϽ���
	public void stage_end() {
		/*if (player.getState().isAI) {
			AIProcess.stage_end();
			return;
		}*/
		player.setStageNum(PlayerIF.STAGE_END);
		ViewManagement.getInstance().printMsg(player.getInfo().getName() + "����");
		/*System.out.println(player.getState().getId().toString()
				+ player.getInfo().getName() + "����");
		player.refreshView();*/
	}
	//�¼ҿ�ʼ
	private void pass(){
		if(Frame_Main.isGameOver){
			return;
		}
		System.out.println(player.getInfo().getName()+"pass");
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ViewManagement.getInstance().refreshAll();
		player.getNextPlayer().process();		
	}
	public boolean isSkilling() {
		return isSkilling;
	}

	public void setSkilling(boolean isSkilling) {
		this.isSkilling = isSkilling;
	}

	public boolean isCanUseCard() {
		return canUseCard;
	}

	public void setCanUseCard(boolean canUseCard) {
		this.canUseCard = canUseCard;
	}

	
}

package sanguosha1.skills.active;

import sanguosha1.card.AbstractCard;
import sanguosha1.card.changed.Virtual_Sha;
import sanguosha1.data.constant.Const_Game;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.player.PlayerIF;
import sanguosha1.skills.ChangeCardIF;
import sanguosha1.skills.SkillIF;

import javax.swing.*;

/**
 * ���ơ�������
 * ɱ==��
 * @author user
 * 
 */
public class ZhaoYun_longdan implements Runnable, SkillIF ,ChangeCardIF{
	AbstractPlayer player;
	Panel_Control pc;
	public ZhaoYun_longdan(AbstractPlayer p) {
		this.player = p;
	}
	
	/**
	 * �ڳ��ƻغϣ�
	 * 		��ʾ����ɱ&������������ɱ
	 * ����Ӧ���ˣ�
	 * 		��ʾ����ɱ&���������������ɱor����ֵ
	 */
	@Override
	public void run() {
		 pc = (Panel_Control) player.getPanel();
		//�������Ӧ����ʱ��
		if(player.getState().isRequest()){
			useAsRequest();
			return;
		}
		//��ʾ����ɱ&��
		SwingUtilities.invokeLater(showShaAndShan);
		//�ȴ�ѡ��
		while(true){
			if(player.getState().getRes()==Const_Game.OK){
				AbstractCard c = pc.getHand().getSelectedList().get(0).getCard();
				//ԭ�е��ƶ���
				c.throwIt(player);
				//�³�һ������ɱ
				new Virtual_Sha(c).use(player, pc.getHand().getTarget().getList().get(0));
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

	/*
	 * ��Ӧ�׶ε�ʹ��
	 */
	private void useAsRequest() {
		// ��ס��Ӧ
		player.getState().setRes(Const_Game.SKILL);
		// ��ʾ���з��ϵ���
		SwingUtilities.invokeLater(showShaAndShan);
		// �ȴ�ѡ��
		while (true) {
			if (player.getState().getRes() == Const_Game.OK) {
				/*AbstractCard c = pc.getHand().getSelectedList().get(0)
						.getCard();*/
				// ��������
				pc.getHand().updateCards();
				player.getState().setRes(getResultType());
				break;
			}
			if (player.getState().getRes() == Const_Game.CANCEL) {
				player.getState().setRes(Const_Game.REDO);
				break;
			}
		}
		synchronized (player.getRequest()) {
			player.getRequest().notify();
		}
	}
	
	@Override
	public String getName() {
		return "����";
	}

	@Override
	public boolean isEnableUse() {
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getResultType() {
		int res = 0;
		switch(player.getRequest().getCurType()){
		case Const_Game.SHA:
			res= Const_Game.SHA;
			break;
		case Const_Game.SHAN:
			res= Const_Game.SHAN;
			break;
		}
		return res;
	}
	
	Runnable showShaAndShan = new Runnable() {
		
		@Override
		public void run() {
			Panel_HandCards ph = pc.getHand();
			ph.unableToUseCard();
			ph.remindToUse(Const_Game.SHA,Const_Game.SHAN);
			ph.setSelectLimit(1);
			ph.disableClick();
			ph.enableOKAndCancel();
		}
	};
}

package sanguosha1.skills.active;

import javax.swing.SwingUtilities;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.data.enums.ErrorMessageType;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.MessageManagement;
import sanguosha1.skills.ChangeCardIF;
import sanguosha1.skills.SkillIF;

/**
 * ��٢�����ȡ�
 * @author user
 *
 */
public class HuaTuo_jijiu implements Runnable ,SkillIF,ChangeCardIF{
	AbstractPlayer player;
	//����������������
	Panel_Control pc;
	
	public HuaTuo_jijiu(AbstractPlayer p){
		this.player = p;
	}
	
	@Override
	public void run() {
		pc = (Panel_Control) player.getPanel();
		//δ������ʱ��
		if(!player.getState().isRequest()){
			MessageManagement.printErroMsg(ErrorMessageType.cannotUseNow);
			synchronized (player.getProcess()) {
				player.getState().setRes(0);
				player.getProcess().notify();
			}
			return;
		}
		//��ʾ����
		SwingUtilities.invokeLater(showRedCards);

		// ��ס��Ӧ
		player.getState().setRes(Const_Game.SKILL);
		// ��ʾ���к�ɫ��
		SwingUtilities.invokeLater(showRedCards);
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
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnableUse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultType() {
		return Const_Game.TAO;
	}

	Runnable showRedCards = new Runnable() {

		@Override
		public void run() {
			Panel_HandCards ph = pc.getHand();
			ph.unableToUseCard();
			ph.remindToUse(Colors.HONGXIN, Colors.FANGKUAI);
			ph.setSelectLimit(1);
			ph.disableClick();
			ph.enableOKAndCancel();
			ph.setTargetCheck(false);
		}
	};
}

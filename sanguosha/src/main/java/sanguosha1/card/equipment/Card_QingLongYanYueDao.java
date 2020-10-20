package sanguosha1.card.equipment;

import sanguosha1.data.constant.Const_Game;
import sanguosha1.data.enums.Colors;
import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * �������µ�
 * @author user
 *
 */
public class Card_QingLongYanYueDao extends AbstractWeaponCard {
	Panel_Control pc;
	
	public Card_QingLongYanYueDao() {
		super();
	}
	public Card_QingLongYanYueDao(int id, int number, Colors color) {
		super(id, number, color);
	}

	
	/**
	 * ��д������Ĵ���
	 */
	@Override
	public void falseTrigger(AbstractPlayer p, AbstractPlayer target) {
		//�����ɱ��ѯ��
		if(!p.hasCard(Const_Game.SHA)){
			return;
		}
		//AI��ʱ��Ч
		if(p.getState().isAI()){
			return;
		}
		pc = (Panel_Control) p.getPanel();
		//��ס���߳�
		p.getProcess().setSkilling(true);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SwingUtilities.invokeLater(ask);
		while(true){
			if(p.getState().getRes() == Const_Game.OK){
				if(p.getRequest().requestSha()){
					List<AbstractPlayer> list = new ArrayList<AbstractPlayer>();
					list.add(target);
//					pc.getHand().getSelectedList().get(0).getCard().use(p, list);
					p.getState().getUsedCard().get(0).use(p, list);
					//new Card_Sha().executeSha(p, target);
					p.getState().setRes(0);
					break;
				}
			}
			if(p.getState().getRes() == Const_Game.CANCEL){
				p.getState().setRes(0);
				break;
			}
		}
		//����
		synchronized (p.getProcess()) {
			ViewManagement.getInstance().getPrompt().clear();
			p.getProcess().setSkilling(false);
			p.getProcess().notify();
		}
	}
	Runnable ask = new Runnable() {
		
		@Override
		public void run() {
			
			Panel_HandCards ph = pc.getHand();
			ph.disableClick();
			ph.unableToUseCard();
			ph.enableOKAndCancel();
			ViewManagement.getInstance().getPrompt().show_Message("�Ƿ񷢶�"+getName());
		}
	};
}

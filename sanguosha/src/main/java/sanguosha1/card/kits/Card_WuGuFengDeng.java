package sanguosha1.card.kits;

import sanguosha1.gui.main.Panel_Control;
import sanguosha1.gui.main.Panel_WuGuFengDeng;
import sanguosha1.player.AbstractPlayer;

import javax.swing.*;
import java.util.List;

/**
 * ��ȷ��
 * 
 * @author user
 * 
 */
public class Card_WuGuFengDeng extends AbstractKitCard {
	public Card_WuGuFengDeng(){}
	
	@Override
	public void use(final AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		if(p.getState().isAI()){
			//TODO
		}else{
			//��ʾѡ�����ȴ�����
			//�߳���ͣ
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					Panel_Control pc = (Panel_Control)p.getPanel();
					Panel_WuGuFengDeng pw = new Panel_WuGuFengDeng(p);
					pc.getMain().add(pw,0);
					pc.getHand().unableToUseCard();
					pw.setLocation(200,pc.getMain().getHeight()/9);
					pc.getMain().validate();
				
				}
			});
			
		}
	
	}
}

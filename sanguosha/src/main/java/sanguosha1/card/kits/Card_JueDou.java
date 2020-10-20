package sanguosha1.card.kits;

import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

import java.util.List;

/**
 * ����
 * @author user
 *
 */
public class Card_JueDou extends AbstractKitCard{
	AbstractPlayer player1;
	AbstractPlayer player2;
	public Card_JueDou(){
		
	}

	/**
	 * ��дuse����
	 */
	@Override
	public void use( AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);
		//ViewManagement.getInstance().printBattleMsg(p.getInfo().getName()+"ʹ��"+this.name);
		AbstractPlayer p2 = players.get(0);
		player1 = p;
		player2 = p2;
		//����
		p.getTrigger().afterMagic();
		//��и
		// �����и����return
		askWuXieKeJi(p, players);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			ViewManagement.getInstance().refreshAll();
			return;
		}
		p.refreshView();
		//ֻҪ����û��ʤ�����ͽ�����ݾ���
		while(p.getAction().jueDou(p2)){
			AbstractPlayer tmp = p;
			p=p2;
			p2 = tmp;
		}
	}

	
}

package sanguosha1.card.kits;

import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

import java.util.List;

/**
 * ��������
 * 
 * @author user
 * 
 */
public class Card_WuZhongShengYou extends AbstractKitCard {
	public Card_WuZhongShengYou() {

	}

	@Override
	public void use(AbstractPlayer p, List<AbstractPlayer> players) {
		super.use(p, players);

		// �����и����return
		askWuXieKeJi(p, players);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "��Ч");
			ViewManagement.getInstance().refreshAll();
			return;
		}
		for (int i = 0; i < 2; i++) {
			p.getAction().addOneCardFromList();
		}
		p.refreshView();
	}

	/**
	 * Ŀ����
	 */
	@Override
	public void targetCheck(Panel_HandCards ph) {
		List<Panel_Player> list = ph.getMain().getPlayers();
		for (Panel_Player pp : list) {
			pp.disableToUse();
		}
	}
}

package sanguosha1.card.kits;

import java.util.List;

import sanguosha1.gui.main.Panel_HandCards;
import sanguosha1.gui.main.Panel_Player;
import sanguosha1.player.AbstractPlayer;
import sanguosha1.service.ViewManagement;

/**
 * 无中生有
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

		// 如果无懈，则return
		askWuXieKeJi(p, players);
		if (isWuXie) {
			ViewManagement.getInstance().printBattleMsg(getName() + "无效");
			ViewManagement.getInstance().refreshAll();
			return;
		}
		for (int i = 0; i < 2; i++) {
			p.getAction().addOneCardFromList();
		}
		p.refreshView();
	}

	/**
	 * 目标检测
	 */
	@Override
	public void targetCheck(Panel_HandCards ph) {
		List<Panel_Player> list = ph.getMain().getPlayers();
		for (Panel_Player pp : list) {
			pp.disableToUse();
		}
	}
}

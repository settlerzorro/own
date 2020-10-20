package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.event.handlers.equipment.KylinBowAbilityCheckEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class KylinBow extends Weapon {

	private static final long serialVersionUID = -5406945570873385619L;

	public KylinBow(int num, Suit suit, int id) {
		super(5, num, suit, id);
	}

	@Override
	public String getName() {
		return "Kylin Bow";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new KylinBowAbilityCheckEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new KylinBowAbilityCheckEventHandler(owner));
	}

}

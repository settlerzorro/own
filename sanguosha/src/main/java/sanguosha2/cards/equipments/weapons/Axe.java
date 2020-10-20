package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.event.handlers.equipment.AxeWeaponAbilitiesCheckEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class Axe extends Weapon {

	private static final long serialVersionUID = 1L;

	public Axe(int num, Suit suit, int id) {
		super(3, num, suit, id);
	}

	@Override
	public String getName() {
		return "Axe";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new AxeWeaponAbilitiesCheckEventHandler(owner));
	}

	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new AxeWeaponAbilitiesCheckEventHandler(owner));
	}
}

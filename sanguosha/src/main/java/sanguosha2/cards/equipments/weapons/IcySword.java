package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.event.handlers.equipment.IcySwordAbilityCheckEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class IcySword extends Weapon {

	private static final long serialVersionUID = 5010996802567818570L;

	public IcySword(int num, Suit suit, int id) {
		super(2, num, suit, id);
	}

	@Override
	public String getName() {
		return "Icy Sword";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new IcySwordAbilityCheckEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new IcySwordAbilityCheckEventHandler(owner));
	}

}

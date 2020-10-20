package sanguosha2.cards.equipments.weapons;

import sanguosha2.core.event.handlers.equipment.AncientFalchionAttackDamageEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class AncientFalchion extends Weapon {

	private static final long serialVersionUID = 3088418134391918826L;

	public AncientFalchion(int num, Suit suit, int id) {
		super(2, num, suit, id);
	}

	@Override
	public String getName() {
		return "Ancient Falchion";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new AncientFalchionAttackDamageEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new AncientFalchionAttackDamageEventHandler(owner));
	}

}

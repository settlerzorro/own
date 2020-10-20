package sanguosha2.cards.equipments.shields;

import sanguosha2.core.event.handlers.equipment.TaichiFormationDodgeArbitrationEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class TaichiFormation extends Shield {

	private static final long serialVersionUID = 1L;

	public TaichiFormation(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Taichi Formation";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new TaichiFormationDodgeArbitrationEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new TaichiFormationDodgeArbitrationEventHandler(owner));
	}

}

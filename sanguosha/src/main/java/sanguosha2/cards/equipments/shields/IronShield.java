package sanguosha2.cards.equipments.shields;

import sanguosha2.core.event.handlers.equipment.IronShieldAttackTargetEuipmentCheckEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class IronShield extends Shield {

	private static final long serialVersionUID = 4370802087723597065L;

	public IronShield(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Iron Shield";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new IronShieldAttackTargetEuipmentCheckEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new IronShieldAttackTargetEuipmentCheckEventHandler(owner));
	}
}

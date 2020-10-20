package sanguosha2.cards.equipments.shields;

import sanguosha2.core.event.handlers.equipment.SilverLionCheckDamageEventHandler;
import sanguosha2.core.event.handlers.equipment.SilverLionUnequipEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class SilverLion extends Shield {

	private static final long serialVersionUID = -4821532886423359596L;

	public SilverLion(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Silver Lion";
	}
	
	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new SilverLionCheckDamageEventHandler(owner));
		game.registerEventHandler(new SilverLionUnequipEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new SilverLionCheckDamageEventHandler(owner));
		/* unequip event handler is remove in @SilverLionUnequipEventHandler */
	}
	
	@Override
	public void onActivated(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new SilverLionCheckDamageEventHandler(owner));
	}

}

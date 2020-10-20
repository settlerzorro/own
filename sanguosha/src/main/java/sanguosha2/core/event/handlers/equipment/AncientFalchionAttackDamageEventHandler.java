package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.damage.AttackDamageModifierEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class AncientFalchionAttackDamageEventHandler extends AbstractEventHandler<AttackDamageModifierEvent> {

	public AncientFalchionAttackDamageEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackDamageModifierEvent> getEventClass() {
		return AttackDamageModifierEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackDamageModifierEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (this.player != event.getDamage().getSource()) {
			return;
		}
		
		if (event.getDamage().getTarget().getHandCount() == 0) {
			event.getDamage().setAmount(event.getDamage().getAmount() + 1);
		}
	}

}

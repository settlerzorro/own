package sanguosha2.core.event.handlers.equipment;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.event.game.basic.AttackEndEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class IronSwordTargetShieldResetEventHandler extends AbstractEventHandler<AttackEndEvent> {
	
	public IronSwordTargetShieldResetEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackEndEvent> getEventClass() {
		return AttackEndEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackEndEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		if (event.target.isEquipped(EquipmentType.SHIELD)) {
			// reactivate target shield abilities (if still present)
			event.target.getShield().onActivated(game, event.target);
		}
		game.removeEventHandler(this);
	}

}

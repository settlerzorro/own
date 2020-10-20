package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.basic.AttackTargetEquipmentCheckEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AttackResolutionGameController.AttackResolutionStage;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RattanArmorAttackTargetEquipmentCheckEventHandler extends AbstractEventHandler<AttackTargetEquipmentCheckEvent> {

	public RattanArmorAttackTargetEquipmentCheckEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackTargetEquipmentCheckEvent> getEventClass() {
		return AttackTargetEquipmentCheckEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackTargetEquipmentCheckEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (!this.player.getPlayerInfo().equals(event.getTarget())) {
			return;
		}
		
		// block NORMAL attacks only
		if (event.getAttackCard().getElement() != Element.NORMAL) {
			return;
		}
		
		throw new GameFlowInterruptedException(() -> {
			event.getController().setStage(AttackResolutionStage.END);
			event.getController().proceed();
		});
	}

}

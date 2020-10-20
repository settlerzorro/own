package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.instants.AOEInstantSpecialTargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.ArrowSalvoTargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.BarbarianInvasionTargetEffectivenessEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.specials.SpecialGameController.SpecialStage;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RattanArmorAOEInstantSpecialTargetEffectivenessEventHandler extends AbstractEventHandler<AOEInstantSpecialTargetEffectivenessEvent> {

	public RattanArmorAOEInstantSpecialTargetEffectivenessEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AOEInstantSpecialTargetEffectivenessEvent> getEventClass() {
		return AOEInstantSpecialTargetEffectivenessEvent.class;
	}

	@Override
	protected void handleIfActivated(AOEInstantSpecialTargetEffectivenessEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (!this.player.equals(event.getCurrentTarget())) {
			return;
		}
		
		// only affects Barbarian Invasion & Arrow Salvo
		if (!(event instanceof BarbarianInvasionTargetEffectivenessEvent || event instanceof ArrowSalvoTargetEffectivenessEvent)) {
			return;
		}
		
		throw new GameFlowInterruptedException(() -> {
			event.getController().setStage(SpecialStage.EFFECT_TAKEN);
			event.getController().proceed();
		});
	}

}

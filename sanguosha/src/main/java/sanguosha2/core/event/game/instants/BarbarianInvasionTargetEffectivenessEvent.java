package sanguosha2.core.event.game.instants;

import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.specials.instants.AOEInstantSpecialGameController;

public class BarbarianInvasionTargetEffectivenessEvent extends GenericAOEInstantSpecialTargetEffectivenessEvent {

	public BarbarianInvasionTargetEffectivenessEvent(PlayerCompleteServer target, AOEInstantSpecialGameController controller) {
		super(target, controller);
	}
	
}

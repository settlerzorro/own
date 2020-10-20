package sanguosha2.core.event.game.instants;

import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.specials.instants.AOEInstantSpecialGameController;

public class BrotherhoodTargetEffectivenessEvent extends GenericAOEInstantSpecialTargetEffectivenessEvent {

	public BrotherhoodTargetEffectivenessEvent(PlayerCompleteServer target, AOEInstantSpecialGameController controller) {
		super(target, controller);
	}

}

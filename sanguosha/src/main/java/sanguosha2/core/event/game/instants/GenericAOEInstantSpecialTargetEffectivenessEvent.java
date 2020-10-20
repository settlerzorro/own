package sanguosha2.core.event.game.instants;

import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.specials.instants.AOEInstantSpecialGameController;

public class GenericAOEInstantSpecialTargetEffectivenessEvent implements AOEInstantSpecialTargetEffectivenessEvent {
	
	private final PlayerCompleteServer target;
	private final AOEInstantSpecialGameController controller;
	
	public GenericAOEInstantSpecialTargetEffectivenessEvent(PlayerCompleteServer target, AOEInstantSpecialGameController controller) {
		this.target = target;
		this.controller = controller;
	}
	
	@Override
	public PlayerCompleteServer getCurrentTarget() {
		return this.target;
	}

	@Override
	public AOEInstantSpecialGameController getController() {
		return this.controller;
	}

}

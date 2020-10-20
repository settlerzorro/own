package sanguosha2.core.event.game.instants;

import sanguosha2.core.server.game.controllers.specials.instants.AOEInstantSpecialGameController;

public interface AOEInstantSpecialTargetEffectivenessEvent extends AOETargetEffectivenessEvent {
	
	public AOEInstantSpecialGameController getController();
}

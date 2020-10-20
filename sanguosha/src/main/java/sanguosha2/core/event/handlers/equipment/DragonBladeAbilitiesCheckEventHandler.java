package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.basic.AttackOnDodgedWeaponAbilitiesCheckEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.specials.instants.DragonBladeGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DragonBladeAbilitiesCheckEventHandler extends AbstractEventHandler<AttackOnDodgedWeaponAbilitiesCheckEvent> {

	public DragonBladeAbilitiesCheckEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackOnDodgedWeaponAbilitiesCheckEvent> getEventClass() {
		return AttackOnDodgedWeaponAbilitiesCheckEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackOnDodgedWeaponAbilitiesCheckEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		if (this.player != event.source) {
			return;
		}
		
		game.pushGameController(new DragonBladeGameController(game, event.source, event.target));
	}

}

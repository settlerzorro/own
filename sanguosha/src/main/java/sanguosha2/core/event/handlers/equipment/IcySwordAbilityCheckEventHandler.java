package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.basic.AttackPreDamageWeaponAbilitiesCheckEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.equipment.IcySwordGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class IcySwordAbilityCheckEventHandler extends AbstractEventHandler<AttackPreDamageWeaponAbilitiesCheckEvent> {

	public IcySwordAbilityCheckEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackPreDamageWeaponAbilitiesCheckEvent> getEventClass() {
		return AttackPreDamageWeaponAbilitiesCheckEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackPreDamageWeaponAbilitiesCheckEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		if (this.player != event.source) {
			return;
		}
		
		if (event.target.getHandCount() == 0 && !event.target.isEquipped()) {
			return;
		}
		
		throw new GameFlowInterruptedException(() -> {
			game.pushGameController(new IcySwordGameController(game, event.source, event.target, event.controller));
			game.getGameController().proceed();
		});
	}

}

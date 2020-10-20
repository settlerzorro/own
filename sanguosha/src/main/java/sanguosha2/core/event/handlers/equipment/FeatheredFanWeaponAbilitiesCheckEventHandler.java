package sanguosha2.core.event.handlers.equipment;

import sanguosha2.commands.game.client.DecisionUIClientCommand;
import sanguosha2.core.event.game.basic.AttackPreAcquisitionWeaponAbilitiesCheckEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AttackGameController.AttackStage;
import sanguosha2.core.server.game.controllers.equipment.FeatheredFanGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class FeatheredFanWeaponAbilitiesCheckEventHandler extends AbstractEventHandler<AttackPreAcquisitionWeaponAbilitiesCheckEvent> {

	public FeatheredFanWeaponAbilitiesCheckEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackPreAcquisitionWeaponAbilitiesCheckEvent> getEventClass() {
		return AttackPreAcquisitionWeaponAbilitiesCheckEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackPreAcquisitionWeaponAbilitiesCheckEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		if (this.player != event.source) {
			return;
		}
		
		if (event.controller.getAttackCard().getElement() == Element.NORMAL) {
			game.pushGameController(new FeatheredFanGameController(game, event.controller));
			throw new GameFlowInterruptedException(() -> {
				event.controller.setStage(AttackStage.TARGET_ACQUISITION);
				connection.sendCommandToAllPlayers(new DecisionUIClientCommand(
					this.player.getPlayerInfo(),
					"Use Feathered Fan?"
				));
			});
		}
	}

}

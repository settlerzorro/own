package sanguosha2.core.event.handlers.equipment;

import sanguosha2.commands.game.client.DecisionUIClientCommand;
import sanguosha2.core.event.game.DodgeArbitrationEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.equipment.TaichiFormationGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class TaichiFormationDodgeArbitrationEventHandler extends AbstractEventHandler<DodgeArbitrationEvent> {

	public TaichiFormationDodgeArbitrationEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<DodgeArbitrationEvent> getEventClass() {
		return DodgeArbitrationEvent.class;
	}

	@Override
	protected void handleIfActivated(DodgeArbitrationEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (!this.player.getPlayerInfo().equals(event.getTarget())) {
			return;
		}
		game.pushGameController(new TaichiFormationGameController(game, this.player));
		connection.sendCommandToAllPlayers(new DecisionUIClientCommand(
			this.player.getPlayerInfo(),
			"Use Taichi Formation?"
		));
		throw new GameFlowInterruptedException();
	}

}

package sanguosha2.core.event.handlers.turn;

import sanguosha2.commands.game.client.DealStartGameUIClientCommmand;
import sanguosha2.core.event.game.turn.DealTurnEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DealTurnEventHandler extends AbstractEventHandler<DealTurnEvent> {
	
	public DealTurnEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public void handleIfActivated(DealTurnEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		throw new GameFlowInterruptedException(() -> {
			connection.sendCommandToPlayer(player.getName(), new DealStartGameUIClientCommmand(game.getCurrentPlayer().getPlayerInfo()));
		});
	}

	@Override
	public Class<DealTurnEvent> getEventClass() {
		return DealTurnEvent.class;
	}

}

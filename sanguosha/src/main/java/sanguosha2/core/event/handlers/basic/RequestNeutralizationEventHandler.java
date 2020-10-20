package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.RequestNeutralizationGameUIClientCommand;
import sanguosha2.core.event.game.basic.RequestNeutralizationEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestNeutralizationEventHandler extends AbstractEventHandler<RequestNeutralizationEvent> {

	public RequestNeutralizationEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestNeutralizationEvent> getEventClass() {
		return RequestNeutralizationEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestNeutralizationEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(this.player.getName(), new RequestNeutralizationGameUIClientCommand(event.getMessage()));
	}

}

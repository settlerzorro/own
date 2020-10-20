package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.RequestDodgeGameUIClientCommand;
import sanguosha2.core.event.game.basic.RequestDodgeEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestDodgeEventHandler extends AbstractEventHandler<RequestDodgeEvent> {

	public RequestDodgeEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestDodgeEvent> getEventClass() {
		return RequestDodgeEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestDodgeEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(
			this.player.getName(),
			new RequestDodgeGameUIClientCommand(event.getTarget(), event.getMessage())
		);
	}

}

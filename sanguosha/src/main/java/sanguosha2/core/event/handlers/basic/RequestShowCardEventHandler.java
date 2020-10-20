package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.RequestShowCardGameUIClientCommand;
import sanguosha2.core.event.game.basic.RequestShowCardEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestShowCardEventHandler extends AbstractEventHandler<RequestShowCardEvent> {

	public RequestShowCardEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestShowCardEvent> getEventClass() {
		return RequestShowCardEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestShowCardEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(this.player.getName(), new RequestShowCardGameUIClientCommand(event.getTarget(), event.getMessage()));
	}

}

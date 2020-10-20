package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.RequestUseCardGameUIClientCommand;
import sanguosha2.core.event.game.basic.RequestUseCardEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestUseCardEventHandler extends AbstractEventHandler<RequestUseCardEvent> {

	public RequestUseCardEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestUseCardEvent> getEventClass() {
		return RequestUseCardEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestUseCardEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(
			this.player.getName(),
			new RequestUseCardGameUIClientCommand(event.getTarget(), event.getMessage(), event.getPredicates())
		);
	}

}

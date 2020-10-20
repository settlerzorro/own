package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.RequestAttackGameUIClientCommand;
import sanguosha2.core.event.game.basic.RequestAttackEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestAttackEventHandler extends AbstractEventHandler<RequestAttackEvent> {

	public RequestAttackEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestAttackEvent> getEventClass() {
		return RequestAttackEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestAttackEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(
			this.player.getName(),
			new RequestAttackGameUIClientCommand(event.getTarget(), event.getMessage())
		);
	}

}

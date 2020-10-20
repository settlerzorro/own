package sanguosha2.core.event.handlers.basic;

import sanguosha2.commands.game.client.DecisionUIClientCommand;
import sanguosha2.core.event.game.basic.RequestDecisionEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RequestDecisionEventHandler extends AbstractEventHandler<RequestDecisionEvent> {

	public RequestDecisionEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<RequestDecisionEvent> getEventClass() {
		return RequestDecisionEvent.class;
	}

	@Override
	protected void handleIfActivated(RequestDecisionEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(
			this.player.getName(),
			new DecisionUIClientCommand(event.getTarget(), event.getMessage())
		);
	}

}

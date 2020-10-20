package sanguosha2.core.event.handlers.turn;

import sanguosha2.core.event.game.turn.DrawTurnEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DrawTurnEventHandler extends AbstractEventHandler<DrawTurnEvent> {

	public DrawTurnEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<DrawTurnEvent> getEventClass() {
		return DrawTurnEvent.class;
	}

	@Override
	protected void handleIfActivated(DrawTurnEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		this.player.clearDisposalArea();
		if (this.player.equals(game.getCurrentPlayer())) {
			game.drawCards(this.player, 2);
		}
	}

}

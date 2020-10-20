package sanguosha2.core.event.handlers.turn;

import sanguosha2.core.event.game.turn.DrawStartTurnEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.TurnGameController.TurnStage;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class SkipDrawTurnEventHandler extends AbstractEventHandler<DrawStartTurnEvent> {

	public SkipDrawTurnEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<DrawStartTurnEvent> getEventClass() {
		return DrawStartTurnEvent.class;
	}

	@Override
	protected void handleIfActivated(DrawStartTurnEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (this.player != game.getCurrentPlayer()) {
			return;
		}

		throw new GameFlowInterruptedException(() -> {
			game.removeEventHandler(this);
			// skip DRAW
			event.turn.setCurrentStage(TurnStage.DEAL_BEGINNING);
			game.getGameController().proceed();
		});
	}

}

package sanguosha2.core.event.handlers.turn;

import sanguosha2.core.event.game.turn.DealStartTurnEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.TurnGameController.TurnStage;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class SkipDealTurnEventHandler extends AbstractEventHandler<DealStartTurnEvent> {

	public SkipDealTurnEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<DealStartTurnEvent> getEventClass() {
		return DealStartTurnEvent.class;
	}

	@Override
	protected void handleIfActivated(DealStartTurnEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (this.player != game.getCurrentPlayer()) {
			return;
		}

		throw new GameFlowInterruptedException(() -> {
			game.removeEventHandler(this);
			// skip DEAL
			event.turn.setCurrentStage(TurnStage.DISCARD_BEGINNING);
			game.getGameController().proceed();
		});
	}

}

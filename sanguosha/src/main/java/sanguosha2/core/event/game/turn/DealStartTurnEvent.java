package sanguosha2.core.event.game.turn;

import sanguosha2.core.server.game.controllers.TurnGameController;

public class DealStartTurnEvent extends AbstractTurnEvent {

	public DealStartTurnEvent(TurnGameController turn) {
		super(turn);
	}

}

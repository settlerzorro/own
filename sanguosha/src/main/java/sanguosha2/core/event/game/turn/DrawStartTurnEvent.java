package sanguosha2.core.event.game.turn;

import sanguosha2.core.server.game.controllers.TurnGameController;

public class DrawStartTurnEvent extends AbstractTurnEvent {

	public DrawStartTurnEvent(TurnGameController turn) {
		super(turn);
	}

}

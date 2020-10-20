package sanguosha2.core.event.game.turn;

import sanguosha2.core.event.game.AbstractGameEvent;
import sanguosha2.core.server.game.controllers.TurnGameController;

public class AbstractTurnEvent extends AbstractGameEvent {
	
	public final TurnGameController turn;
	
	public AbstractTurnEvent(TurnGameController turn) {
		this.turn = turn;
	}
	
}

package sanguosha2.core.server.game.controllers.specials.delayed;

import sanguosha2.cards.Card;
import sanguosha2.cards.Card.Suit;
import sanguosha2.core.event.handlers.turn.SkipDrawTurnEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.TurnGameController;
import sanguosha2.utils.DelayedStackItem;
import sanguosha2.utils.DelayedType;

public class StarvationArbitrationController extends AbstractDelayedArbitrationController {

	public StarvationArbitrationController(Game game, PlayerCompleteServer target, TurnGameController turn) {
		super(game, target, turn);
	}

	@Override
	protected void takeEffect() {
		this.game.registerEventHandler(new SkipDrawTurnEventHandler(this.target));
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.target + " will start arbitration for Starvation, use Neutralization?";
	}

	@Override
	protected void beforeEnd() {
		DelayedStackItem item = this.target.removeDelayed(DelayedType.STARVATION);
		this.game.getDeck().discard(item.delayed);
	}

	@Override
	protected boolean isArbitrationEffective(Card card) {
		return card.getSuit() != Suit.CLUB;
	}

}

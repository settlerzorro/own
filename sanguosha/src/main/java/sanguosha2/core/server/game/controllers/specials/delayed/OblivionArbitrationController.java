package sanguosha2.core.server.game.controllers.specials.delayed;

import sanguosha2.cards.Card;
import sanguosha2.cards.Card.Suit;
import sanguosha2.core.event.handlers.turn.SkipDealTurnEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.TurnGameController;
import sanguosha2.utils.DelayedStackItem;
import sanguosha2.utils.DelayedType;

public class OblivionArbitrationController extends AbstractDelayedArbitrationController {

	public OblivionArbitrationController(Game game, PlayerCompleteServer target, TurnGameController turn) {
		super(game, target, turn);
	}

	@Override
	public boolean isArbitrationEffective(Card card) {
		return card.getSuit() != Suit.HEART;
	}

	@Override
	protected void takeEffect() {
		this.game.registerEventHandler(new SkipDealTurnEventHandler(this.target));
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.target + " will start arbitration for Oblivion, use Neutralization?";
	}

	@Override
	protected void beforeEnd() {
		DelayedStackItem item = this.target.removeDelayed(DelayedType.OBLIVION);
		this.game.getDeck().discard(item.delayed);
	}

}

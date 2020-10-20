package sanguosha2.core.server.game.controllers.specials.delayed;

import sanguosha2.cards.Card;
import sanguosha2.cards.Card.Suit;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.DamageGameController;
import sanguosha2.core.server.game.controllers.TurnGameController;
import sanguosha2.utils.DelayedStackItem;
import sanguosha2.utils.DelayedType;

public class LightningArbitrationController extends AbstractDelayedArbitrationController {
	
	public LightningArbitrationController(Game game, PlayerCompleteServer target, TurnGameController turn) {
		super(game, target, turn);
	}

	@Override
	public boolean isArbitrationEffective(Card card) {
		return card.getSuit() == Suit.SPADE && card.getNumber() >= 2 && card.getNumber() <= 9;
	}

	@Override
	protected void takeEffect() {
		this.game.pushGameController(new DamageGameController(new Damage(3, Element.THUNDER, null, this.target), this.game));
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.target + " will start arbitration for Lightning, use Neutralization?";
	}

	@Override
	protected void beforeEnd() {
		DelayedStackItem item = this.target.removeDelayed(DelayedType.LIGHTNING);
		if (this.effective) {
			this.game.getDeck().discard(item.delayed);
		} else {
			PlayerCompleteServer next = this.game.getNextPlayerAlive(this.target);
			// WARNING: Potential infinite loop if more than 2 Lightnings exist
			while (next.hasDelayedType(DelayedType.LIGHTNING)) {
				next = this.game.getNextPlayerAlive(next);
			}
			next.pushDelayed(item.delayed, item.type);
		}
	}

}

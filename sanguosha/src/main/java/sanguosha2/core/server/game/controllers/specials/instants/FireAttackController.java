package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.cards.Card;
import sanguosha2.core.event.game.basic.RequestShowCardEvent;
import sanguosha2.core.event.game.basic.RequestUseCardEvent;
import sanguosha2.core.event.game.basic.RequestUseCardEvent.RequestUseCardPredicate;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.controllers.DamageGameController;
import sanguosha2.core.server.game.controllers.interfaces.CardSelectableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class FireAttackController extends SingleTargetInstantSpecialGameController implements CardSelectableGameController {
	
	private Card shownCard;

	public FireAttackController(PlayerInfo source, PlayerInfo target, Game game) {
		super(source, target, game);
		this.shownCard = null;
	}

	@Override
	protected void takeEffect() {
		if (this.shownCard == null) {
			// ineffective if target has no card left on hand
			if (this.target.getHandCount() == 0) {
				this.stage = this.stage.nextStage();
				this.proceed();
				return;
			}
			try {
				this.game.emit(new RequestShowCardEvent(
					this.target.getPlayerInfo(),
					this.source + " used Fire Attack on you, please show a card."
				));
			} catch (GameFlowInterruptedException e) {
				e.resume();
			}
		} else {
			// ineffective if source has no card left on hand
			if (this.source.getHandCount() == 0) {
				this.stage = this.stage.nextStage();
				this.proceed();
				return;
			}
			try {
				this.game.emit(
					new RequestUseCardEvent(
						this.source.getPlayerInfo(),
						"Discard a card of suit " + this.shownCard.getSuit().toString() + "to finish Fire Attack?"
					).addPredicate(RequestUseCardPredicate.sameSuit(this.shownCard.getSuit()))
				);
			} catch (GameFlowInterruptedException e) {
				e.resume();
			}
		}
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Fire Attack on " + this.target + ", use Neutralization?";
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		if (this.shownCard == null) {
			this.shownCard = card;
			this.target.showCard(card);
		} else {
			// check if Fire Attack is effective
			this.stage = this.stage.nextStage();
			if (card != null) {
				try {
					// TODO: convert to controller
					this.source.discardCard(card);
				} catch (InvalidPlayerCommandException e) {
					e.printStackTrace();
				}
				this.game.pushGameController(new DamageGameController(new Damage(1, Element.FIRE, this.source, this.target), this.game));
			}
		}
	}

}

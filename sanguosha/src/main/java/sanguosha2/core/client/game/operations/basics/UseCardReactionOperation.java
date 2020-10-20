package sanguosha2.core.client.game.operations.basics;

import java.util.Collection;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.PlayerCardSelectionInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardReactionOperation;
import sanguosha2.core.event.game.basic.RequestUseCardEvent.RequestUseCardPredicate;
import sanguosha2.core.player.PlayerCardZone;

public class UseCardReactionOperation extends AbstractCardReactionOperation {
	
	private final Collection<RequestUseCardPredicate> predicates;
	
	public UseCardReactionOperation(
		String message,
		Collection<RequestUseCardPredicate> predicates
	) {
		super(message);
		this.predicates = predicates;
	}

	@Override
	protected boolean isCancelEnabled() {
		return true;
	}

	@Override
	protected boolean isCardActivatable(Card card) {
		for (RequestUseCardPredicate predicate : this.predicates) {
			if (!predicate.test(card)) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new PlayerCardSelectionInGameServerCommand(card, PlayerCardZone.HAND);
	}

}

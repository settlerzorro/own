package sanguosha2.core.event.game.basic;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

import sanguosha2.cards.Card;
import sanguosha2.cards.Card.Suit;
import sanguosha2.core.player.PlayerInfo;

public class RequestUseCardEvent extends AbstractSingleTargetPlayerReactionEvent {
	
	private Collection<RequestUseCardPredicate> predicates;
	
	public static interface RequestUseCardPredicate extends Predicate<Card>, Serializable {
		
		public static RequestUseCardPredicate sameSuit(Suit suit) {
			return card -> card.getSuit() == suit;
		}
		
	}
	
	public RequestUseCardEvent(PlayerInfo target, String message) {
		super(target, message);
		this.predicates = new HashSet<>();
	}
	
	public RequestUseCardEvent addPredicate(RequestUseCardPredicate predicate) {
		this.predicates.add(predicate);
		return this;
	}
	
	public Collection<RequestUseCardPredicate> getPredicates() {
		return this.predicates;
	}

}

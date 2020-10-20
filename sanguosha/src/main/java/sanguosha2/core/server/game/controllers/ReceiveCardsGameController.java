package sanguosha2.core.server.game.controllers;

import java.util.Collection;

import sanguosha2.cards.Card;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.utils.EnumWithNextStage;

public class ReceiveCardsGameController extends AbstractGameController {

	public static enum ReceiveCardStage implements EnumWithNextStage<ReceiveCardStage> {
		ADD_CARDS,
		END,
	}
	
	private PlayerCompleteServer target;
	private Collection<Card> cards;
	private ReceiveCardStage stage;
	
	public ReceiveCardsGameController(Game game, PlayerCompleteServer target, Collection<Card> cards) {
		super(game);
		this.target = target;
		this.cards = cards;
		this.stage = ReceiveCardStage.ADD_CARDS;
	}

	@Override
	public void proceed() {
		switch(this.stage) {
			case ADD_CARDS:
				this.stage = this.stage.nextStage();
				this.target.addCards(this.cards);
				this.proceed();
				break;
			case END:
				this.onUnloaded();
				this.game.getGameController().proceed();
				break;
		}

	}

}

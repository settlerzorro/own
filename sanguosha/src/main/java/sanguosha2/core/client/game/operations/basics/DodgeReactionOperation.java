package sanguosha2.core.client.game.operations.basics;

import sanguosha2.cards.Card;
import sanguosha2.cards.basics.Dodge;
import sanguosha2.commands.game.server.ingame.DodgeReactionInGameServerCommand;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardReactionOperation;

public class DodgeReactionOperation extends AbstractCardReactionOperation {

	public DodgeReactionOperation(String message) {
		super(message);
	}

	@Override
	protected boolean isCardActivatable(Card card) {
		return card instanceof Dodge;
	}
	
	@Override
	protected boolean isCancelEnabled() {
		return true;
	}

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new DodgeReactionInGameServerCommand(card);
	}

}

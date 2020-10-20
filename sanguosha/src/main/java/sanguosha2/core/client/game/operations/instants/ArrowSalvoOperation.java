package sanguosha2.core.client.game.operations.instants;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateArrowSalvoInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardUsageOperation;

public class ArrowSalvoOperation extends AbstractCardUsageOperation {

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new InitiateArrowSalvoInGameServerCommand(card);
	}

}

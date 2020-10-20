package sanguosha2.core.client.game.operations.instants;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateBrotherhoodInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardUsageOperation;

public class BrotherhoodOperation extends AbstractCardUsageOperation {

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new InitiateBrotherhoodInGameServerCommand(card);
	}

}

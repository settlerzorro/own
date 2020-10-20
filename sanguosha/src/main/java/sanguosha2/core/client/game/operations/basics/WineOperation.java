package sanguosha2.core.client.game.operations.basics;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.UseWineInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardUsageOperation;

public class WineOperation extends AbstractCardUsageOperation {

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new UseWineInGameServerCommand(card);
	}
	
	
}

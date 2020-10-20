package sanguosha2.core.client.game.operations.delayed;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.UseLightningInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractCardUsageOperation;

public class LightningOperation extends AbstractCardUsageOperation {

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new UseLightningInGameServerCommand(card);
	}

}

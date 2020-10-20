package sanguosha2.core.client.game.operations;

import sanguosha2.cards.Card;
import sanguosha2.cards.equipments.Equipment;
import sanguosha2.commands.game.server.ingame.EquipInGameServerCommand;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;

public class EquipOperation extends AbstractCardUsageOperation {

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new EquipInGameServerCommand((Equipment) card);
	}

}

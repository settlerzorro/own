package sanguosha2.core.client.game.operations.instants;

import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateDuelInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractSingleTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class DuelOperation extends AbstractSingleTargetCardOperation {

	@Override
	protected InGameServerCommand getCommand() {
		return new InitiateDuelInGameServerCommand(this.targetUI.getPlayer().getPlayerInfo(), ((CardUI) this.activator).getCard());
	}

	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			other.setActivatable(true);
		}
		panelUI.setCancelEnabled(true);
	}

}

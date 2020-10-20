package sanguosha2.core.client.game.operations.instants;

import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateStealInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractSingleTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerSimple;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class StealOperation extends AbstractSingleTargetCardOperation {

	@Override
	protected InGameServerCommand getCommand() {
		return new InitiateStealInGameServerCommand(this.targetUI.getPlayer().getPlayerInfo(), ((CardUI) this.activator).getCard());
	}

	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		int numPlayersAlive = panelUI.getNumberOfPlayersAlive();
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			PlayerSimple otherPlayer = other.getPlayer();
			if (
				panelUI.getSelf().isPlayerInDistance(otherPlayer, numPlayersAlive) &&
				(otherPlayer.getHandCount() > 0 || otherPlayer.isEquipped() || !otherPlayer.getDelayedQueue().isEmpty())
			) {
				other.setActivatable(true);
			}
		}
		panelUI.setCancelEnabled(true);
	}

}

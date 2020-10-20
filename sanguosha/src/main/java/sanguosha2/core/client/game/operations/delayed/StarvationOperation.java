package sanguosha2.core.client.game.operations.delayed;

import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateStarvationInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractSingleTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;
import sanguosha2.utils.DelayedType;

public class StarvationOperation extends AbstractSingleTargetCardOperation {

	@Override
	protected InGameServerCommand getCommand() {
		return new InitiateStarvationInGameServerCommand(this.targetUI.getPlayer().getPlayerInfo(), ((CardUI) this.activator).getCard());
	}

	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			if (panelUI.getSelf().isPlayerInDistance(other.getPlayer(), panelUI.getNumberOfPlayersAlive()) && !other.getPlayer().hasDelayedType(DelayedType.STARVATION)) {
				other.setActivatable(true);
			}
		}
		panelUI.setCancelEnabled(true);
	}
}

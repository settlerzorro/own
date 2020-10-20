package sanguosha2.core.client.game.operations.instants;

import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateFireAttackInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractSingleTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class FireAttackOperation extends AbstractSingleTargetCardOperation {

	@Override
	protected InGameServerCommand getCommand() {
		return new InitiateFireAttackInGameServerCommand(this.targetUI.getPlayer().getPlayerInfo(), ((CardUI) this.activator).getCard());
	}

	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		if (panelUI.getSelf().getHandCount() > 1) {
			// can use Fire Attack on self if more than one card on hand
			panelUI.getHeroUI().setActivatable(true);
		}
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			if (other.getPlayer().getHandCount() > 0) {
				other.setActivatable(true);
			}
		}
		panelUI.setCancelEnabled(true);
	}

}

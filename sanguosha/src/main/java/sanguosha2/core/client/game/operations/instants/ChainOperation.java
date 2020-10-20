package sanguosha2.core.client.game.operations.instants;

import java.util.LinkedList;
import java.util.Queue;

import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateChainInGameServerCommand;
import sanguosha2.core.client.game.operations.AbstractMultiTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class ChainOperation extends AbstractMultiTargetCardOperation {

	public ChainOperation() {
		super(0, 2);
	}

	@Override
	protected InGameServerCommand getCommand() {
		Queue<PlayerInfo> queue = new LinkedList<>();
		for (PlayerUI player : this.targets) {
			queue.add(player.getPlayer().getPlayerInfo());
		}
		return new InitiateChainInGameServerCommand(queue, ((CardUI) this.activator).getCard());
	}

	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		panelUI.getHeroUI().setActivatable(true);
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			other.setActivatable(true);
		}
		panelUI.setCancelEnabled(true);
	}

}

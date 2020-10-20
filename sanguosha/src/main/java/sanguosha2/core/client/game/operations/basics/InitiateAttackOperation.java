package sanguosha2.core.client.game.operations.basics;

import java.util.stream.Collectors;

import sanguosha2.cards.basics.Attack;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.InitiateAttackInGameServerCommand;
import sanguosha2.core.client.game.event.InitiateAttackClientGameEvent;
import sanguosha2.core.client.game.operations.AbstractMultiTargetCardOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerComplete;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class InitiateAttackOperation extends AbstractMultiTargetCardOperation {

	public InitiateAttackOperation() {
		super(1, 1);
	}

	@Override
	protected InGameServerCommand getCommand() {
		return new InitiateAttackInGameServerCommand(
			this.source,
			this.targets.stream().map(target -> target.getPlayer().getPlayerInfo()).collect(Collectors.toSet()),
			(Attack) ((CardUI) this.activator).getCard()
		);
	}
	
	@Override
	protected void setupTargetSelection() {
		ClientGameUI<? extends Hero> panelUI = this.panel.getContent();
		PlayerComplete self = panelUI.getSelf();
		for (PlayerUI other : panelUI.getOtherPlayersUI()) {
			if (self.isPlayerInAttackRange(other.getPlayer(), panelUI.getNumberOfPlayersAlive())) {
				other.setActivatable(true);
			}
		}
		panelUI.setCancelEnabled(true);
		this.panel.emit(new InitiateAttackClientGameEvent(true, this));
	}

}

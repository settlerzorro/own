package sanguosha2.commands.game.client;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;

public abstract class AbstractSingleTargetOperationGameClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = 1L;
	
	protected final PlayerInfo target;

	public AbstractSingleTargetOperationGameClientCommand(PlayerInfo target) {
		this.target = target;
	}

	@Override
	protected final void execute(GamePanel<? extends Hero> panel) {
		if (panel.getContent().getSelf().getPlayerInfo().equals(this.target)) {
			panel.pushOperation(this.getOperation());
		} else {
			panel.getContent().getOtherPlayerUI(this.target).showCountdownBar();
		}
	}
	
	protected abstract Operation getOperation();

}

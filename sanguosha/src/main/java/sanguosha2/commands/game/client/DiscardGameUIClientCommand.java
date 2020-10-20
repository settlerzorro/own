package sanguosha2.commands.game.client;

import sanguosha2.core.client.game.operations.DiscardOperation;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.player.PlayerInfo;

public class DiscardGameUIClientCommand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;
	
	private final int amount;
	
	public DiscardGameUIClientCommand(PlayerInfo target, int amount) {
		super(target);
		this.amount = amount;
	}
	
	@Override
	protected Operation getOperation() {
		return new DiscardOperation(this.amount);
	}

}

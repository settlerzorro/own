package sanguosha2.commands.game.client;

import sanguosha2.core.client.game.operations.DecisionOperation;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.player.PlayerInfo;

public class DecisionUIClientCommand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;
	
	private final String messsage;
	
	public DecisionUIClientCommand(PlayerInfo target, String message) {
		super(target);
		this.messsage = message;
	}

	@Override
	protected Operation getOperation() {
		return new DecisionOperation(this.messsage);
	}

}

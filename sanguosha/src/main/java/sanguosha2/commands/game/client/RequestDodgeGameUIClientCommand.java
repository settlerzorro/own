package sanguosha2.commands.game.client;

import sanguosha2. core.client.game.operations.Operation;
import sanguosha2. core.client.game.operations.basics.DodgeReactionOperation;
import sanguosha2. core.player.PlayerInfo;

public class RequestDodgeGameUIClientCommand extends AbstractSingleTargetOperationGameClientCommand {
	
	private static final long serialVersionUID = 1L;

	private final String message;
	
	public RequestDodgeGameUIClientCommand(PlayerInfo target, String message) {
		super(target);
		this.message = message;
	}

	@Override
	protected Operation getOperation() {
		return new DodgeReactionOperation(this.message);
	}

}

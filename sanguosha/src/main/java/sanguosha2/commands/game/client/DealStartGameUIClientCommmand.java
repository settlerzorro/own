package sanguosha2.commands.game.client;

import sanguosha2.core.client.game.operations.DealOperation;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.player.PlayerInfo;

public class DealStartGameUIClientCommmand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;
	
	public DealStartGameUIClientCommmand(PlayerInfo target) {
		super(target);
	}

	@Override
	protected Operation getOperation() {
		return new DealOperation();
	}

}

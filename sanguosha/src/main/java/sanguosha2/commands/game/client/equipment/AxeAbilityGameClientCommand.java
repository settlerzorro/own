package sanguosha2.commands.game.client.equipment;

import sanguosha2.commands.game.client.AbstractSingleTargetOperationGameClientCommand;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.equipment.AxeOperation;
import sanguosha2.core.player.PlayerInfo;

public class AxeAbilityGameClientCommand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;

	public AxeAbilityGameClientCommand(PlayerInfo target) {
		super(target);
	}

	@Override
	protected Operation getOperation() {
		return new AxeOperation();
	}

}

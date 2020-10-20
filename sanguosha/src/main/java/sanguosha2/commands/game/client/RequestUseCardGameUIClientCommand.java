package sanguosha2.commands.game.client;

import java.util.Collection;

import sanguosha2. core.client.game.operations.Operation;
import sanguosha2. core.client.game.operations.basics.UseCardReactionOperation;
import sanguosha2. core.event.game.basic.RequestUseCardEvent.RequestUseCardPredicate;
import sanguosha2. core.player.PlayerInfo;

public class RequestUseCardGameUIClientCommand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;
	
	private final String message;
	private final Collection<RequestUseCardPredicate> predicates;
	
	public RequestUseCardGameUIClientCommand(
		PlayerInfo target,
		String message,
		Collection<RequestUseCardPredicate> predicates
	) {
		super(target);
		this.message = message;
		this.predicates = predicates;
	}


	@Override
	protected Operation getOperation() {
		return new UseCardReactionOperation(this.message, this.predicates);
	}

}

package sanguosha2.core.client.game.event;

import sanguosha2. core.client.game.operations.MultiTargetOperation;

public class InitiateAttackClientGameEvent extends AbstractLifecycleClientGameEvent {
	
	public final MultiTargetOperation operation;

	public InitiateAttackClientGameEvent(boolean isStart, MultiTargetOperation operation) {
		super(isStart);
		this.operation = operation;
	}

}

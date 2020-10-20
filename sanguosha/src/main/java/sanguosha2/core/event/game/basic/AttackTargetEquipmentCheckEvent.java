package sanguosha2.core.event.game.basic;

import sanguosha2.cards.basics.Attack;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.controllers.AttackResolutionGameController;

public class AttackTargetEquipmentCheckEvent extends AbstractSingleTargetGameEvent {
	
	private final AttackResolutionGameController controller;
	private Attack card;

	public AttackTargetEquipmentCheckEvent(PlayerInfo targetInfo, Attack card, AttackResolutionGameController controller) {
		super(targetInfo);
		this.card = card;
		this.controller = controller;
	}
	
	public AttackResolutionGameController getController() {
		return this.controller;
	}
	
	public Attack getAttackCard() {
		return this.card;
	}

}

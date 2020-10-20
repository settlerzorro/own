package sanguosha2.core.event.game.basic;

import sanguosha2.core.event.game.AbstractGameEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.AttackResolutionGameController;

public class AttackOnDodgedWeaponAbilitiesCheckEvent extends AbstractGameEvent {
	
	public final PlayerCompleteServer source;
	public final PlayerCompleteServer target;
	public final AttackResolutionGameController controller;
	
	public AttackOnDodgedWeaponAbilitiesCheckEvent(
		PlayerCompleteServer source,
		PlayerCompleteServer target,
		AttackResolutionGameController controller
	) {
		this.source = source;
		this.target = target;
		this.controller = controller;
	}

}

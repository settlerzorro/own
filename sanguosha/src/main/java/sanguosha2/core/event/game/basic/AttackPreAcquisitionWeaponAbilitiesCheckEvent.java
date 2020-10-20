package sanguosha2.core.event.game.basic;

import sanguosha2.core.event.game.AbstractGameEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.AttackGameController;

public class AttackPreAcquisitionWeaponAbilitiesCheckEvent extends AbstractGameEvent {
	
	public final PlayerCompleteServer source;
	public final AttackGameController controller;
	
	public AttackPreAcquisitionWeaponAbilitiesCheckEvent(PlayerCompleteServer source, AttackGameController controller) {
		this.source = source;
		this.controller = controller;
	}

}

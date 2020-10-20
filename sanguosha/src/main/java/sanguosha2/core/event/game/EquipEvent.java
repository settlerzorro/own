package sanguosha2.core.event.game;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.core.player.PlayerCompleteServer;

public class EquipEvent extends AbstractGameEvent {

	public final PlayerCompleteServer player;
	public final Equipment equipment;
	
	public EquipEvent(PlayerCompleteServer player, Equipment equipment) {
		this.player = player;
		this.equipment = equipment;
	}
}

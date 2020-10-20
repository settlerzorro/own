package sanguosha2.core.event.game;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.player.PlayerCompleteServer;

public class UnequipEvent extends AbstractGameEvent {
	
	public final PlayerCompleteServer player;
	public final EquipmentType equipmentType;
	
	public UnequipEvent(PlayerCompleteServer player, EquipmentType equipmentType) {
		this.player = player;
		this.equipmentType = equipmentType;
	}

}

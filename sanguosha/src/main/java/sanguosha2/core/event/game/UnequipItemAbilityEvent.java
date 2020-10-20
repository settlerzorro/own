package sanguosha2.core.event.game;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.controllers.UnequipGameController;

public class UnequipItemAbilityEvent extends AbstractGameEvent {

	public final PlayerCompleteServer player;
	public final EquipmentType equipmentType;
	public final UnequipGameController controller;
	
	public UnequipItemAbilityEvent(PlayerCompleteServer player, EquipmentType equipmentType, UnequipGameController controller) {
		this.player = player;
		this.equipmentType = equipmentType;
		this.controller = controller;
	}
}

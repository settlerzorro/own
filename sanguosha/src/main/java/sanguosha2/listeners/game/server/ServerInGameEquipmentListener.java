package sanguosha2.listeners.game.server;

import java.util.Set;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.commands.game.client.sync.SyncCommandsUtil;
import sanguosha2.commands.game.client.sync.equipment.SyncEquipGameUIClientCommand;
import sanguosha2.commands.game.client.sync.equipment.SyncUnequipGameUIClientCommand;
import sanguosha2.core.server.GameRoom;
import sanguosha2.listeners.game.EquipmentListener;

public class ServerInGameEquipmentListener extends ServerInGamePlayerListener implements EquipmentListener {
	
	public ServerInGameEquipmentListener(String name, Set<String> allNames, GameRoom room) {
		super(name, allNames, room);
	}
	
	@Override
	public void onEquipped(Equipment equipment) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncEquipGameUIClientCommand(name, equipment)
			)
		);
	}

	@Override
	public void onUnequipped(EquipmentType type) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncUnequipGameUIClientCommand(name, type)
			)	
		);
	}

}

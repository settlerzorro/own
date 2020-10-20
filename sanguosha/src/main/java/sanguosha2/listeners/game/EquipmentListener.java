package sanguosha2.listeners.game;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.cards.equipments.Equipment.EquipmentType;

public interface EquipmentListener 
{
	/**
	 * Require that equipment != null
	 * @param equipment
	 */
	public void onEquipped(Equipment equipment);
	/**
	 * un-equip an equipment, require: type = Equipment.SOMETYPE
	 * @param type
	 */
	public void onUnequipped(EquipmentType type);
}

package sanguosha2.core.server.game.controllers.interfaces;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.core.server.game.controllers.GameController;

public interface EquipmentUsableGameController extends GameController {

	public void onEquipped(Equipment equipment);
	
}

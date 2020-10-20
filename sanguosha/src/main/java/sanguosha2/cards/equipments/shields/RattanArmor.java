package sanguosha2.cards.equipments.shields;

import sanguosha2.core.event.handlers.equipment.RattanArmorAOEInstantSpecialTargetEffectivenessEventHandler;
import sanguosha2.core.event.handlers.equipment.RattanArmorAttackTargetEquipmentCheckEventHandler;
import sanguosha2.core.event.handlers.equipment.RattanArmorCheckDamageEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

public class RattanArmor extends Shield {

	private static final long serialVersionUID = -4377220192386216241L;
	
	public RattanArmor(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Rattan Armor";
	}

	@Override
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		game.registerEventHandler(new RattanArmorCheckDamageEventHandler(owner));
		game.registerEventHandler(new RattanArmorAttackTargetEquipmentCheckEventHandler(owner));
		game.registerEventHandler(new RattanArmorAOEInstantSpecialTargetEffectivenessEventHandler(owner));
	}
	
	@Override
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		game.removeEventHandler(new RattanArmorCheckDamageEventHandler(owner));
		game.removeEventHandler(new RattanArmorAttackTargetEquipmentCheckEventHandler(owner));
		game.removeEventHandler(new RattanArmorAOEInstantSpecialTargetEffectivenessEventHandler(owner));
	}

}

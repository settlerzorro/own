package sanguosha2.core.event.handlers.equipment;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.event.game.basic.AttackPostAcquisitionWeaponAbilitiesCheckEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class IronSwordWeaponAbilitiesCheckEventHandler extends AbstractEventHandler<AttackPostAcquisitionWeaponAbilitiesCheckEvent> {

	public IronSwordWeaponAbilitiesCheckEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<AttackPostAcquisitionWeaponAbilitiesCheckEvent> getEventClass() {
		return AttackPostAcquisitionWeaponAbilitiesCheckEvent.class;
	}

	@Override
	protected void handleIfActivated(AttackPostAcquisitionWeaponAbilitiesCheckEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		if (this.player != event.source) {
			return;
		}
		
		if (event.target.isEquipped(EquipmentType.SHIELD)) {
			// deactivate target shield abilities
			event.target.getShield().onDeactivated(game, event.target);
			game.registerEventHandler(new IronSwordTargetShieldResetEventHandler(this.player));
		}
		
	}

}

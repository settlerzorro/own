package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.damage.TargetEquipmentCheckDamageEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class SilverLionCheckDamageEventHandler extends AbstractTargetEquipmentCheckDamageEventHandler {

	public SilverLionCheckDamageEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	protected void handleIfActivated(TargetEquipmentCheckDamageEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		Damage damage = event.getDamage();
		if (!this.player.equals(damage.getTarget())) {
			return;
		}
		
		if (damage.getAmount() > 1) {
			damage.setAmount(1);
		}
		
	}

}

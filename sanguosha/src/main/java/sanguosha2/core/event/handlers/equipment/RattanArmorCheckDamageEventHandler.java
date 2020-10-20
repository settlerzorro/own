package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.damage.TargetEquipmentCheckDamageEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Damage;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class RattanArmorCheckDamageEventHandler extends AbstractTargetEquipmentCheckDamageEventHandler {

	public RattanArmorCheckDamageEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	protected void handleIfActivated(TargetEquipmentCheckDamageEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		Damage damage = event.getDamage();
		if (!this.player.equals(damage.getTarget())) {
			return;
		}
		
		if (damage.getElement() == Element.FIRE) {
			damage.setAmount(damage.getAmount() + 1);
		}
	}

}

package sanguosha2.core.event.handlers.equipment;

import sanguosha2.core.event.game.damage.TargetEquipmentCheckDamageEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;

public abstract class AbstractTargetEquipmentCheckDamageEventHandler extends AbstractEventHandler<TargetEquipmentCheckDamageEvent> {

	public AbstractTargetEquipmentCheckDamageEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public final Class<TargetEquipmentCheckDamageEvent> getEventClass() {
		return TargetEquipmentCheckDamageEvent.class;
	}

}

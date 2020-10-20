package sanguosha2.core.event.handlers.equipment;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.event.game.UnequipItemAbilityEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.HealGameController;
import sanguosha2.core.server.game.controllers.UnequipGameController.UnequipStage;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class SilverLionUnequipEventHandler extends AbstractEventHandler<UnequipItemAbilityEvent> {

	public SilverLionUnequipEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	protected void handleIfActivated(UnequipItemAbilityEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (!this.player.equals(event.player) || event.equipmentType != EquipmentType.SHIELD) {
			return;
		}
		
		// remove event handler here because unequip event happens before the heal
		game.removeEventHandler(this);
		if (this.player.isDamaged()) {
			throw new GameFlowInterruptedException(() -> {
				event.controller.setStage(UnequipStage.END);
				game.pushGameController(new HealGameController(this.player.getPlayerInfo(), this.player.getPlayerInfo(), game));
				game.getGameController().proceed();
			});
		}
	}

	@Override
	public Class<UnequipItemAbilityEvent> getEventClass() {
		return UnequipItemAbilityEvent.class;
	}

}

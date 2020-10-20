package sanguosha2.core.server.game.controllers;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.equipments.Equipment;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;
import sanguosha2.utils.EnumWithNextStage;

public class EquipGameController extends AbstractGameController {

	public static enum EquipStage implements EnumWithNextStage<EquipStage> {
		UNEQUIP,
		EQUIP,
		END;
	}
	
	private EquipStage stage;
	private final PlayerCompleteServer player;
	private final Equipment equipment;
	
	public EquipGameController(Game game, PlayerCompleteServer player, Equipment equipment) {
		super(game);
		this.player = player;
		this.equipment = equipment;
		this.stage = EquipStage.UNEQUIP;
	}

	@Override
	public void proceed() {
		switch (this.stage) {
			case UNEQUIP:
				try {
					// TODO: convert to controller
					this.player.removeCardFromHand(this.equipment);
				} catch (InvalidPlayerCommandException e) {
					
				}
				
				// discard old equipment (if any) at the same slot
				if (this.player.isEquipped(this.equipment.getEquipmentType())) {
					Equipment old  = this.player.getEquipment(this.equipment.getEquipmentType());
					this.game.pushGameController(new UnequipGameController(this.game, this.player, this.equipment.getEquipmentType())
						.setNextController(new RecycleCardsGameController(this.game, this.player,new HashSet<sanguosha2.cards.Card>(){{add(old);}}))
					);
				}
				this.stage = this.stage.nextStage();
				this.game.getGameController().proceed();
				break;
			case EQUIP:
				try {
					this.player.equip(this.equipment);
					this.equipment.onEquipped(this.game, this.player);
					this.stage = this.stage.nextStage();
					this.proceed();
				} catch (InvalidPlayerCommandException e) {
					e.printStackTrace();
				}
				break;
			case END:
				this.onUnloaded();
				this.game.getGameController().proceed();
				break;
		}
	}

}

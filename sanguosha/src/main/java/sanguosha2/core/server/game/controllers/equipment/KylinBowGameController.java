package sanguosha2.core.server.game.controllers.equipment;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.cards.equipments.Equipment;
import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.event.game.basic.RequestDecisionEvent;
import sanguosha2.core.event.game.instants.PlayerCardSelectionEvent;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AbstractGameController;
import sanguosha2.core.server.game.controllers.AttackResolutionGameController;
import sanguosha2.core.server.game.controllers.AttackResolutionGameController.AttackResolutionStage;
import sanguosha2.core.server.game.controllers.RecycleCardsGameController;
import sanguosha2.core.server.game.controllers.UnequipGameController;
import sanguosha2.core.server.game.controllers.interfaces.CardSelectableGameController;
import sanguosha2.core.server.game.controllers.interfaces.DecisionRequiredGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class KylinBowGameController
	extends AbstractGameController
	implements CardSelectableGameController, DecisionRequiredGameController {

	private final PlayerCompleteServer source;
	private final PlayerCompleteServer target;
	private final AttackResolutionGameController controller;
	private Boolean confirmed;
	
	public KylinBowGameController(Game game, PlayerCompleteServer source, PlayerCompleteServer target, AttackResolutionGameController controller) {
		super(game);
		this.source = source;
		this.target = target;
		this.controller = controller;
		this.confirmed = null;
		
	}

	@Override
	public void proceed() {
		if (this.confirmed == null) {
			try {
				this.game.emit(new RequestDecisionEvent(this.source.getPlayerInfo(), "Use Kylin Bow?"));
			} catch (GameFlowInterruptedException e) {
				// won't receive GameFlowInterruptedException
			}
			return;
		} else if (this.confirmed == true) {
			try {
				this.confirmed = false; // enter exit code path
				this.game.emit(new PlayerCardSelectionEvent(
					this.source.getPlayerInfo(),
					this.target.getPlayerInfo(),
						new HashSet<PlayerCardZone>(){{add(PlayerCardZone.EQUIPMENT);}},
						new HashSet<EquipmentType>(){{add(EquipmentType.HORSEPLUS);add(EquipmentType.HORSEMINUS);}}
				));
			} catch (GameFlowInterruptedException e) {
				// won't receive GameFlowInterruptedException
			}
		} else {
			this.onUnloaded();
			this.game.getGameController().proceed();
		}
	}

	@Override
	public void onDecisionMade(boolean confirmed) {
		this.confirmed = confirmed;
		this.controller.setStage(AttackResolutionStage.DAMAGE_MODIFIERS);
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		Equipment equipment = (Equipment) card;
		this.game.pushGameController(
			new UnequipGameController(this.game, this.target, equipment.getEquipmentType())
				.setNextController(new RecycleCardsGameController(this.game, this.target, new HashSet<Card>(){{add(equipment);}}))
		);
	}

}

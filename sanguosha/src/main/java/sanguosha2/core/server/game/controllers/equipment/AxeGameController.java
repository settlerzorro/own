package sanguosha2.core.server.game.controllers.equipment;

import java.util.HashSet;
import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.cards.equipments.Equipment;
import sanguosha2.core.client.game.listener.ClientEventListener;
import sanguosha2.core.heroes.original.HeroOriginal;
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
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class AxeGameController
	extends AbstractGameController
	implements CardSelectableGameController, DecisionRequiredGameController {
	
	private final PlayerCompleteServer source;
	private final AttackResolutionGameController controller;

	public AxeGameController(Game game, PlayerCompleteServer source, AttackResolutionGameController controller) {
		super(game);
		this.source = source;
		this.controller = controller;
	}

	@Override
	public void proceed() {
		this.onUnloaded();
		this.game.getGameController().proceed();
	}

	@Override
	public void onDecisionMade(boolean confirmed) {
		if (confirmed) {
			this.controller.setStage(AttackResolutionStage.PRE_DAMAGE_WEAPON_ABILITIES);
		} else {
			this.controller.setStage(AttackResolutionStage.END);
		}
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		switch (zone) {
			case HAND:
				try {
					// TODO: convert to discard controller
					this.source.discardCard(card);
				} catch (InvalidPlayerCommandException e) {
					e.printStackTrace();
				}
				break;
			case EQUIPMENT:
				Equipment equipment = (Equipment) card;
				this.game.pushGameController(
					new UnequipGameController(this.game, this.source, equipment.getEquipmentType())
						.setNextController(new RecycleCardsGameController(this.game, this.source, new HashSet<Card>(){{add(equipment);}}))
				);
				break;
			default:
				break;
		}
	}

}

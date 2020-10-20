package sanguosha2.core.server.game.controllers.equipment;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.cards.equipments.Equipment;
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
import sanguosha2.exceptions.server.game.GameStateErrorException;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class IcySwordGameController
	extends AbstractGameController
	implements CardSelectableGameController, DecisionRequiredGameController {

	private final PlayerCompleteServer source;
	private final PlayerCompleteServer target;
	private final AttackResolutionGameController controller;
	private int numCardDiscarded;
	private Boolean confirmed;
	
	public IcySwordGameController(Game game, PlayerCompleteServer source, PlayerCompleteServer target, AttackResolutionGameController controller) {
		super(game);
		this.source = source;
		this.target = target;
		this.controller = controller;
		this.numCardDiscarded = 0;
		this.confirmed = null;
	}

	@Override
	public void proceed() {
		if (this.confirmed == null) {
			try {
				this.game.emit(new RequestDecisionEvent(this.source.getPlayerInfo(), "Use Icy Sword?"));
			} catch (GameFlowInterruptedException e) {
				// won't receive GameFlowInterruptedException
			}
			return;
		} else if (this.confirmed == true) {
			if (this.numCardDiscarded == 2) {
				this.onUnloaded();
				this.game.getGameController().proceed();
			} else {
				try {
					this.game.emit(new PlayerCardSelectionEvent(
						this.source.getPlayerInfo(),
						this.target.getPlayerInfo(),
							new HashSet<PlayerCardZone>(){{add(PlayerCardZone.HAND);add(PlayerCardZone.EQUIPMENT);}}
					));
				} catch (GameFlowInterruptedException e) {
					// won't receive GameFlowInterruptedException
				}
			}
		} else {
			this.onUnloaded();
			this.game.getGameController().proceed();
		}
	}

	@Override
	public void onDecisionMade(boolean confirmed) {
		this.confirmed = confirmed;
		if (!confirmed) {
			this.controller.setStage(AttackResolutionStage.DAMAGE_MODIFIERS);
		} else {
			this.controller.setStage(AttackResolutionStage.END);
		}
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		this.numCardDiscarded++;
		switch(zone) {
			case HAND:
				try {
					// TODO: convert to discard controller
					List<Card> cards = this.target.getCardsOnHand();
					this.target.discardCard(cards.get(new Random().nextInt(cards.size())));
				} catch (InvalidPlayerCommandException e) {
					e.printStackTrace();
				}
				break;
			case EQUIPMENT:
				Equipment equipment = (Equipment) card;
				this.game.pushGameController(
					new UnequipGameController(this.game, this.target, equipment.getEquipmentType())
						.setNextController(new RecycleCardsGameController(this.game, this.target, new HashSet<Card>(){{add(equipment);}}))
				);
				break;
			default:
				throw new GameStateErrorException("Icy Sword: Card is not from HAND or EQUIPMENT");
		}
		
		// exit early if target has no more card to discard
		if (this.target.getHandCount() == 0 && !this.target.isEquipped()) {
			this.numCardDiscarded = 2;
		}
	}

}

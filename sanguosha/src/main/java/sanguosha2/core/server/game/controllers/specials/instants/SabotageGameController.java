package sanguosha2.core.server.game.controllers.specials.instants;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.cards.equipments.Equipment;
import sanguosha2.core.event.game.instants.PlayerCardSelectionEvent;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.RecycleCardsGameController;
import sanguosha2.core.server.game.controllers.UnequipGameController;
import sanguosha2.core.server.game.controllers.interfaces.CardSelectableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SabotageGameController extends SingleTargetInstantSpecialGameController implements CardSelectableGameController {

	public SabotageGameController(PlayerInfo source, PlayerInfo target, Game game) {
		super(source, target, game);
	}
	
	@Override
	protected void takeEffect() {
		if (this.target.getHandCount() == 0 && !this.target.isEquipped() && this.target.getDelayedQueue().isEmpty()) {
			// if no card left on target, Sabotage is ineffective
			this.stage = this.stage.nextStage();
			this.proceed();
			return;
		}
		try {
			this.game.emit(new PlayerCardSelectionEvent(
				this.source.getPlayerInfo(),
				this.target.getPlayerInfo(),
				new HashSet<PlayerCardZone>(){{add(PlayerCardZone.HAND);add(PlayerCardZone.EQUIPMENT);add(PlayerCardZone.DELAYED);}}
			));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.source + " used Sabotage on " + this.target + ", use Neutralization?";
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		switch(zone) {
			case HAND:
				try {
					// TODO: convert to discard controller
					List<Card> cards = this.target.getCardsOnHand();
					// By default discard a random card from hand
					this.target.discardCard(cards.get(new Random().nextInt(cards.size())));
				} catch (InvalidPlayerCommandException e) {
					e.printStackTrace();
				}
				break;
			case EQUIPMENT:
				try {
					Equipment equipment = (Equipment) card;
					this.game.pushGameController(
						new UnequipGameController(this.game, this.target, equipment.getEquipmentType())
							.setNextController(new RecycleCardsGameController(this.game, this.target, new HashSet<Card>(){{add(equipment);}}))
					);
				} catch (ClassCastException e) {
					e.printStackTrace();
				}
				break;
			case DELAYED:
				this.target.removeDelayed(card);
				this.game.pushGameController(new RecycleCardsGameController(this.game, this.target,  new HashSet<Card>(){{add(card);}}));
				break;
		}
		this.stage = this.stage.nextStage();
	}
}

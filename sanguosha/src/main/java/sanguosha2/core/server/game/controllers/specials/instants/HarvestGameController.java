package sanguosha2.core.server.game.controllers.specials.instants;

import java.util.*;
import java.util.stream.Collectors;

import sanguosha2.cards.Card;
import sanguosha2.core.event.game.instants.AOETargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.GenericAOEInstantSpecialTargetEffectivenessEvent;
import sanguosha2.core.event.game.instants.HarvestCardSelectionEvent;
import sanguosha2.core.event.handlers.instant.HarvestCardSelectionEventHandler;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.ReceiveCardsGameController;
import sanguosha2.core.server.game.controllers.RecycleCardsGameController;
import sanguosha2.core.server.game.controllers.interfaces.CardSelectableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class HarvestGameController extends AOEInstantSpecialGameController implements CardSelectableGameController {

	private Map<Card, Boolean> selectableCards;
	private List<PlayerCompleteServer> players;

	public HarvestGameController(PlayerInfo source, Game game) {
		super(source, game, true);
		this.selectableCards = null;
		this.players = game.getPlayersAlive();
		// register handlers
		this.players.forEach(player -> game.registerEventHandler(new HarvestCardSelectionEventHandler(player)));
		this.selectableCards = game.getDeck().drawMany(game.getNumberOfPlayersAlive()).stream().collect(Collectors.toMap(card -> card, card -> false));
		
		// for client UI update only, won't cause interruption
		try {
			this.game.emit(new HarvestCardSelectionEvent(null, new HashMap<>(this.selectableCards)));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
	}
	
	@Override
	protected void takeEffect() {
		try {
			this.game.emit(new HarvestCardSelectionEvent(this.currentTarget.getPlayerInfo(), new HashMap<>(this.selectableCards)));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
	}
	
	@Override
	protected String getNeutralizationMessage() {
		return this.currentTarget + " will select a card from Harvest, use Neutralization?";
	}

	@Override
	public void onCardSelected(Card card, PlayerCardZone zone) {
		this.stage = this.stage.nextStage();
		// TODO: sanity check
		this.selectableCards.replace(card, true);
		
		// for client UI update only, won't cause interruption
		try {
			this.game.emit(new HarvestCardSelectionEvent(null, new HashMap<>(this.selectableCards)));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
		
		this.game.pushGameController(new ReceiveCardsGameController(this.game, this.currentTarget,new HashSet<Card>(){{add(card);}}));
	}

	@Override
	protected AOETargetEffectivenessEvent getTargetEffectivenessEvent() {
		return new GenericAOEInstantSpecialTargetEffectivenessEvent(this.currentTarget, this);
	}

	@Override
	protected void onSettled() {
		// for client UI update only, won't cause interruption
		try {
			this.game.emit(new HarvestCardSelectionEvent(null, null));
		} catch (GameFlowInterruptedException e) {
			e.resume();
		}
		// remove handlers
		this.players.forEach(player -> game.removeEventHandler(new HarvestCardSelectionEventHandler(player)));
		// discard all unselected cards
		Set<Card> unselected = this.selectableCards.entrySet().stream().filter(entry -> !entry.getValue()).map(entry -> entry.getKey()).collect(Collectors.toSet());
		if (unselected.size() > 0) {
			this.game.pushGameController(new RecycleCardsGameController(this.game, this.source, unselected));
		}
	}

}

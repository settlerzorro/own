package sanguosha2.listeners.game.server;

import java.util.Set;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.client.sync.SyncCommandsUtil;
import sanguosha2.commands.game.client.sync.disposal.SyncCardDisposedGameUIClientCommand;
import sanguosha2.commands.game.client.sync.disposal.SyncCardShownGameUIClientCommand;
import sanguosha2.commands.game.client.sync.disposal.SyncCardUsedGameUIClientCommand;
import sanguosha2.commands.game.client.sync.disposal.SyncDisposalAreaRefreshGameUIClientCommand;
import sanguosha2.core.Deck;
import sanguosha2.core.server.GameRoom;
import sanguosha2.listeners.game.CardDisposalListener;

public class ServerInGameCardDisposalListener extends ServerInGamePlayerListener implements CardDisposalListener {
	
	private final Deck deck;
	
	public ServerInGameCardDisposalListener(String name, Set<String> playerNames, GameRoom room) {
		super(name, playerNames, room);
		this.deck = room.getGame().getDeck();
	}

	@Override
	public void onCardUsed(Card card) {
		this.deck.discard(card);
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncCardUsedGameUIClientCommand(name, card)
			)
		);
	}

	@Override
	public void onCardDisposed(Card card) {
		// TODO: some abilities might affect disposed cards
		this.deck.discard(card);
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncCardDisposedGameUIClientCommand(name, card)
			)
		);
	}
	
	@Override
	public void onCardShown(Card card) {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncCardShownGameUIClientCommand(name, card)
			)
		);
	}

	@Override
	public void refresh() {
		room.sendCommandToPlayers(
			SyncCommandsUtil.generateMapForSameCommand(
				name, 
				otherNames, 
				new SyncDisposalAreaRefreshGameUIClientCommand()
			)
		);
	}

}

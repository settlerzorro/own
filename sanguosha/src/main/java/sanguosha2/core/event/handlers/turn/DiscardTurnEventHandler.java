package sanguosha2.core.event.handlers.turn;

import java.util.stream.Collectors;

import sanguosha2.commands.game.client.DiscardGameUIClientCommand;
import sanguosha2.core.event.game.turn.DiscardTurnEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DiscardTurnEventHandler extends AbstractEventHandler<DiscardTurnEvent> {

	public DiscardTurnEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<DiscardTurnEvent> getEventClass() {
		return DiscardTurnEvent.class;
	}

	@Override
	protected void handleIfActivated(DiscardTurnEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		if (!this.player.equals(game.getCurrentPlayer())) {
			return;
		}
		int amount = this.player.getHandCount() - this.player.getCardOnHandLimit();
		if (amount > 0) {
			throw new GameFlowInterruptedException(() -> {
				connection.sendCommandToPlayers(
					game.getPlayersInfo().stream().collect(
						Collectors.toMap(
							info -> info.getName(), 
							info -> new DiscardGameUIClientCommand(this.player.getPlayerInfo(), amount)
						)
					)
				);
			});
		}
	}

}

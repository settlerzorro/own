package sanguosha2.core.event.handlers.instant;

import sanguosha2.commands.game.client.ShowCardSelectionPanelUIClientCommand;
import sanguosha2.core.event.game.instants.PlayerCardSelectionEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class PlayerCardSelectionEventHandler extends AbstractEventHandler<PlayerCardSelectionEvent> {

	public PlayerCardSelectionEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<PlayerCardSelectionEvent> getEventClass() {
		return PlayerCardSelectionEvent.class;
	}

	@Override
	protected void handleIfActivated(PlayerCardSelectionEvent event, Game game, ConnectionController connection)
		throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(
			this.player.getName(),
			new ShowCardSelectionPanelUIClientCommand(
				event.getSource(),
				event.getTarget(),
				event.getZones(),
				event.getEquipmentTypes()
			)
		);
	}

}

package sanguosha2.core.event.handlers.instant;

import sanguosha2.commands.game.client.ShowHarvestCardSelectionPaneUIClientCommand;
import sanguosha2.core.event.game.instants.HarvestCardSelectionEvent;
import sanguosha2.core.event.handlers.AbstractEventHandler;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.ConnectionController;
import sanguosha2.core.server.game.Game;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class HarvestCardSelectionEventHandler extends AbstractEventHandler<HarvestCardSelectionEvent> {

	public HarvestCardSelectionEventHandler(PlayerCompleteServer player) {
		super(player);
	}

	@Override
	public Class<HarvestCardSelectionEvent> getEventClass() {
		return HarvestCardSelectionEvent.class;
	}

	@Override
	protected void handleIfActivated(HarvestCardSelectionEvent event, Game game, ConnectionController connection) throws GameFlowInterruptedException {
		connection.sendCommandToPlayer(this.player.getName(), new ShowHarvestCardSelectionPaneUIClientCommand(event.getTarget(), event.getSelectableCards()));
	}

}

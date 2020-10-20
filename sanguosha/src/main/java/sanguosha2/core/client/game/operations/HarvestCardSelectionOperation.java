package sanguosha2.core.client.game.operations;

import java.util.Map;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.PlayerCardSelectionInGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.ui.game.custom.HarvestSelectionPane;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;

public class HarvestCardSelectionOperation implements Operation {
	
	private GamePanel<? extends Hero> panel;
	private final PlayerInfo target;
	private final Map<Card, Boolean> selectableCards;
	
	public HarvestCardSelectionOperation(PlayerInfo target, Map<Card, Boolean> selectableCards) {
		this.target = target;
		this.selectableCards = selectableCards;
	}
	
	@Override
	public void onCardClicked(CardUI card) {
		this.panel.getContent().removeSelectionPane();
		this.panel.popOperation();
		this.panel.getChannel().send(new PlayerCardSelectionInGameServerCommand(card.getCard(), null));
	}

	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		this.panel = panel;
		panel.getContent().displayCustomizedSelectionPaneAtCenter(new HarvestSelectionPane(this.selectableCards, this.target.getName(), panel));
	}

}

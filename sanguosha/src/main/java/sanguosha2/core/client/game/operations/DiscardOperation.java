package sanguosha2.core.client.game.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sanguosha2.commands.game.server.ingame.DiscardInGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;

public class DiscardOperation implements Operation {
	
	private GamePanel<? extends Hero> panel;
	private List<CardUI> cards = new ArrayList<>();
	private final int amount;
	
	public DiscardOperation(int amount) {
		this.amount = amount;
	}
	
	@Override
	public void onConfirmed() {
		for(CardUI cardUI : panel.getContent().getCardRackUI().getCardUIs()) {
			cardUI.setActivatable(false);
		}
		panel.getContent().setConfirmEnabled(false);
		panel.getContent().clearMessage();
		panel.getChannel().send(new DiscardInGameServerCommand(cards.stream().map(ui -> ui.getCard()).collect(Collectors.toList())));
	}

	@Override
	public void onCardClicked(CardUI card) {
		if (cards.remove(card)) {
			card.setActivated(false);
			if (cards.size() == 0) {
				panel.getContent().setConfirmEnabled(false);
			}
		} else {
			card.setActivated(true);
			if (cards.size() == 0) {
				panel.getContent().setConfirmEnabled(true);
			}
			cards.add(card);
			if (cards.size() > amount) {
				cards.get(0).setActivated(false);
				cards.remove(0);
			}
		}
	}

	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		this.panel = panel;
		ClientGameUI<? extends Hero> panelUI = panel.getContent();
		panelUI.setMessage("Select " + this.amount + " cards to discard");
		panelUI.showCountdownBar();
		for(CardUI cardUI : panelUI.getCardRackUI().getCardUIs()) {
			cardUI.setActivatable(true);
		}
	}

}

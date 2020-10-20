package sanguosha2.core.client.game.operations.instants;

import sanguosha2.cards.Card;
import sanguosha2.cards.specials.instant.Neutralization;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.commands.game.server.ingame.NeutralizationReactionInGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.operations.AbstractCardReactionOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;

public class NeutralizationOperation extends AbstractCardReactionOperation {

	public NeutralizationOperation(String message) {
		super(message);
	}

	@Override
	public void onEnded() {
       if (this.card != null) {
           this.card.setActivated(false);
           this.card = null;
       }
       panel.getContent().setConfirmEnabled(false);
       panel.getContent().setCancelEnabled(false);
       for (CardUI ui : this.panel.getContent().getCardRackUI().getCardUIs()) {
           ui.setActivatable(false);
       }
	}

	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		if (panel.getCurrentOperation() instanceof NeutralizationOperation) {
			panel.getCurrentOperation().onEnded();
			panel.popOperation();
		}
		super.onActivated(panel, source);
	}

	@Override
	protected boolean isCardActivatable(Card card) {
		return card instanceof Neutralization;
	}
	
	@Override
	protected boolean isCancelEnabled() {
		return true;
	}

	@Override
	protected InGameServerCommand getCommand(Card card) {
		if (card == null) {
			return new NeutralizationReactionInGameServerCommand(null, null);
		} else {
			return new NeutralizationReactionInGameServerCommand(this.panel.getContent().getSelf().getPlayerInfo(), card);
		}
	}
}

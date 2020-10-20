package sanguosha2.core.client.game.operations;

import sanguosha2.cards.Card;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.ui.game.CardGui;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;

public abstract class AbstractCardUsageOperation implements Operation {

	private CardGui card;
	private GamePanel<? extends Hero> panel;
	protected PlayerInfo source;

	@Override
	public final void onCanceled() {
		this.onDeactivated();
	}

	@Override
	public final void onConfirmed() {
		this.onDeactivated();
		panel.getCurrentOperation().onConfirmed();
		panel.getChannel().send(getCommand(card == null ? null : card.getCard()));
	}
	
	@Override
	public final void onCardClicked(CardUI card) {
		this.onDeactivated();
		if (card != this.card) {
			panel.getCurrentOperation().onCardClicked(card);
		}
	}

	@Override
	public final void onEnded() {
		this.onDeactivated();
		panel.getCurrentOperation().onEnded();
	}

	@Override
	public final void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		this.panel = panel;
		this.card = (CardGui) source;
		ClientGameUI<? extends Hero> panelUI = panel.getContent();
		panelUI.setMessage("Use " + this.card.getCard() + "?");
		this.source = panelUI.getSelf().getPlayerInfo();
		panel.getContent().setConfirmEnabled(true);
		panel.getContent().setCancelEnabled(true);
	}
	
	@Override
	public void onDeactivated() {
		card.setActivated(false);
		panel.getContent().setConfirmEnabled(false);
		panel.getContent().setCancelEnabled(false);
		panel.getContent().clearMessage();
		panel.popOperation();
	}
	
	protected abstract InGameServerCommand getCommand(Card card);

}

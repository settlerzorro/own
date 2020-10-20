package sanguosha2.core.client.game.operations.basics;

import sanguosha2.cards.Card;
import sanguosha2.cards.basics.Attack;
import sanguosha2.commands.game.server.ingame.AttackReactionInGameServerCommand;
import sanguosha2.commands.game.server.ingame.InGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.AttackReactionClientGameEvent;
import sanguosha2.core.client.game.operations.AbstractCardReactionOperation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;

public class AttackReactionOperation extends AbstractCardReactionOperation {

	public AttackReactionOperation(String message) {
		super(message);
	}

	@Override
	protected boolean isCardActivatable(Card card) {
		return card instanceof Attack;
	}
	
	@Override
	protected boolean isCancelEnabled() {
		return true;
	}
	
	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		super.onActivated(panel, source);
		panel.emit(new AttackReactionClientGameEvent(true));
	}
	
	@Override
	public void onDeactivated() {
		super.onDeactivated();
		this.panel.emit(new AttackReactionClientGameEvent(false));
	}

	@Override
	protected InGameServerCommand getCommand(Card card) {
		return new AttackReactionInGameServerCommand(this.panel.getContent().getSelf().getPlayerInfo(), card);
	}
	
}

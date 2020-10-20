package sanguosha2.core.client.game.operations;

import sanguosha2.commands.game.server.ingame.EndStageInGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.event.EnableAttackClientGameEvent;
import sanguosha2.core.heroes.Hero;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.EquipmentUI;

public class DealOperation implements Operation {

	private GamePanel<? extends Hero> panel;
	
	@Override
	public void onEnded() {
		this.onDeactivated();
		// resetting weapon/skills, etc.
		panel.getChannel().send(new EndStageInGameServerCommand());
	}
	
	/**
	 * <p>{@inheritDoc}</p>
	 * 
	 * <p>
	 * Deal itself will not enable confirm button,
	 * but other operations may, so this is a way to
	 * clean up the UI changes made by Deal operation
	 * </p>
	 */
	@Override
	public void onConfirmed() {
		this.onDeactivated();
	}
	
	@Override
	public void onCardClicked(CardUI card) {
		panel.pushOperation(card.getCard().generateOperation(), card);
	}
	
	@Override
	public void onEquipmentClicked(EquipmentUI equipment) {
		panel.pushOperation(equipment.getEquipment().generateOperation(), equipment);
	}

	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		this.panel = panel;
		ClientGameUI<? extends Hero> panelUI = panel.getContent();
		for(CardUI cardUI : panelUI.getCardRackUI().getCardUIs()) {
			if (cardUI.getCard().isActivatable(panelUI)) {
				cardUI.setActivatable(true);
			}
		}
		panelUI.setEndEnabled(true);
		this.panel.emit(new EnableAttackClientGameEvent(true));
		// if (player.getWeapon()...) check weapon use
		// skills
	}
	
	@Override
	public void onDeactivated() {
		ClientGameUI<? extends Hero> panelUI = panel.getContent();
		panelUI.setEndEnabled(false);
		for(CardUI cardUI : panelUI.getCardRackUI().getCardUIs()) {
			cardUI.setActivatable(false);
		}
		this.panel.emit(new EnableAttackClientGameEvent(false));
		this.panel.popOperation();
	}

}

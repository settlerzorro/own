package sanguosha2.listeners.game.client;

import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.EquipmentUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public interface PlayerActionListener {

	default public void onCardClicked(CardUI card) {}
	
	default public void onPlayerClicked(PlayerUI player) {}
	
	default public void onEquipmentClicked(EquipmentUI equipment) {}
	
	default public void onDelayedClicked(CardUI card) {}
	
	default public void onConfirmed() {}
	
	default public void onCanceled() {}
	
	default public void onEnded() {}
}

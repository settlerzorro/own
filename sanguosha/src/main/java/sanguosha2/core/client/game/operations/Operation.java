package sanguosha2.core.client.game.operations;

import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.listeners.game.client.PlayerActionListener;
import sanguosha2.ui.game.interfaces.Activatable;
/**
 * An operation that listens to user actions (confirm, cancel, select cards_img/targets, etc.)
 * @author Harry
 *
 */
public interface Operation extends PlayerActionListener {
	
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source);
	
	default public void onDeactivated() {}
}

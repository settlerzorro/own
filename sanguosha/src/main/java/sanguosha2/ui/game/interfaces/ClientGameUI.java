package sanguosha2.ui.game.interfaces;

import java.util.List;

import javax.swing.JPanel;

import sanguosha2.core.GameState;
import sanguosha2.core.client.ClientPanelUI;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;

public interface ClientGameUI<T extends Hero> extends ClientPanelUI, GameState {

	/**
	 * invoked to enable/disable confirm button
	 * @param isEnabled
	 */
	public void setConfirmEnabled(boolean isEnabled);
	
	/**
	 * invoked to enable/disable cancel button
	 * @param isEnabled
	 */
	public void setCancelEnabled(boolean isEnabled);
	
	/**
	 * invoked to enable/disable end button
	 * @param isEnabled
	 */
	public void setEndEnabled(boolean isEnabled);
	
	public PlayerUI getOtherPlayerUI(String name);
	
	public PlayerUI getOtherPlayerUI(PlayerInfo other);

	public HeroUI<T> getHeroUI();

	public CardRackUI getCardRackUI();
	
	public EquipmentRackUI getEquipmentRackUI();

	public List<? extends PlayerUI> getOtherPlayersUI();
	
	public void displayCustomizedSelectionPaneAtCenter(JPanel panel);
	
	public JPanel getSelectionPane();
	
	public void removeSelectionPane();
	
	public void setMessage(String message);
	
	public void clearMessage();

	public void showCountdownBar();
	
}

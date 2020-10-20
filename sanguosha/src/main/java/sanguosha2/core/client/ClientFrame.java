package sanguosha2.core.client;

public interface ClientFrame {

	/**
	 * TODO: add check to ensure that this panel is received in the correct order
	 * @param panel
	 */
	void onNewPanelDisplayed(ClientPanel<? extends ClientPanelUI> panel);

	public <T extends ClientPanelUI> ClientPanel<T> getPanel();


}

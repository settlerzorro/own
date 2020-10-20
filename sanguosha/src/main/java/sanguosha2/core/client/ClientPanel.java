package sanguosha2.core.client;

import sanguosha2.net.client.ClientMessageListener;

/**
 * @author Harry
 *
 */
public interface ClientPanel<T extends ClientPanelUI> {
	
	public T getContent();
	
	public ClientMessageListener getMessageListener();

}

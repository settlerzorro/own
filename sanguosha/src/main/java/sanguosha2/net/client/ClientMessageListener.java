package sanguosha2.net.client;

import sanguosha2.net.Message;

public interface ClientMessageListener {

	public void onMessageReceived(Message message);
}

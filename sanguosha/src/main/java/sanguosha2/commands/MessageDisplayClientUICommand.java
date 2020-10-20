package sanguosha2.commands;

import sanguosha2. core.client.ClientFrame;
import sanguosha2. net.Connection;
import sanguosha2. net.Message;

public class MessageDisplayClientUICommand implements Command<ClientFrame> {
	private static final long serialVersionUID = -856542896052269531L;
	private final Message message;
	
	public MessageDisplayClientUICommand(Message message) {
		this.message = message;
	}
	
	@Override
	public void execute(ClientFrame ui, Connection connection) {
		ui.getPanel().getMessageListener().onMessageReceived(message);
	}

}

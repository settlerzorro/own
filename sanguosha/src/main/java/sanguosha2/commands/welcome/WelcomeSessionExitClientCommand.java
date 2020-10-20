package sanguosha2.commands.welcome;

import java.io.IOException;

import sanguosha2. net.Connection;
import sanguosha2. commands.Command;
import sanguosha2. core.client.ClientFrame;

import java.io.IOException;

public class WelcomeSessionExitClientCommand implements Command<ClientFrame> {

	private static final long serialVersionUID = -53592037900598522L;

	@Override
	public void execute(ClientFrame object, Connection connection) {
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(0); // for now...
	}

}

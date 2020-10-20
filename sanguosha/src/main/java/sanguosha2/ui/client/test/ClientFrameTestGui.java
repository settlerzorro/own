package sanguosha2.ui.client.test;

import java.util.Random;

import javax.swing.JFrame;

import sanguosha2.commands.lobby.test.EnterRoomTestLobbyServerCommand;
import sanguosha2.commands.welcome.EnterLobbyServerCommand;
import sanguosha2.core.Constants;
import sanguosha2.core.client.ClientFrame;
import sanguosha2.core.client.ClientPanel;
import sanguosha2.core.client.ClientPanelUI;
import sanguosha2.net.Connection;
import sanguosha2.net.ConnectionListener;
import sanguosha2.net.client.Client;
import sanguosha2.utils.Log;

/**
 * A test gui that goes directly into the room
 * 
 * @author Harry
 *
 */
public class ClientFrameTestGui implements ClientFrame, ConnectionListener {
	private final JFrame frame;
	private ClientPanel<? extends ClientPanelUI> panel;

	public ClientFrameTestGui() {
		this.frame = new JFrame("Sanguosha");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocation(new Random().nextInt(Constants.SCREEN_WIDTH-1000), new Random().nextInt(Constants.SCREEN_HEIGHT-720));
		frame.setVisible(true);
	}
	
	public synchronized void toRoom(Client client) {
		try {
			Connection connection = client.connect();
			wait();
			connection.send(new EnterLobbyServerCommand());
			wait();
			connection.send(new EnterRoomTestLobbyServerCommand());
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void onNewPanelDisplayed(ClientPanel<? extends ClientPanelUI> panel) {
		if (this.panel != null) {
			frame.remove(this.panel.getContent().getPanel());
		}
		this.panel = panel;
		frame.add(panel.getContent().getPanel());
		frame.pack();
		notify();
	}
	
	@Override
	public void onConnectionLost(Connection connection, String message) {
		Log.error("Client Frame", message);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends ClientPanelUI> ClientPanel<T> getPanel() {
		return (ClientPanel<T>) panel;
	}

}

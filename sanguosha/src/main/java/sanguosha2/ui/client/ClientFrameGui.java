package sanguosha2.ui.client;

import javax.swing.JFrame;

import sanguosha2.core.client.ClientFrame;
import sanguosha2.core.client.ClientPanel;
import sanguosha2.core.client.ClientPanelUI;
import sanguosha2.net.Connection;
import sanguosha2.net.ConnectionListener;
import sanguosha2.net.client.Client;
import sanguosha2.utils.Log;

public class ClientFrameGui implements ClientFrame, ConnectionListener {
	private final JFrame frame;
	private ClientPanel<? extends ClientPanelUI> panel;

	public ClientFrameGui(Client client) {
		frame = new JFrame("Sanguosha");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(800, 0);
		frame.setVisible(true);
		onNewPanelDisplayed(new ConnectScreenGui(client));
	}

	@Override
	public synchronized void onNewPanelDisplayed(ClientPanel<? extends ClientPanelUI> panel) {
		if (this.panel != null) {
			frame.remove(this.panel.getContent().getPanel());
		}
		this.panel = panel;
		frame.add(panel.getContent().getPanel());
		frame.pack();
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

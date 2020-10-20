package sanguosha2.ui.client;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import sanguosha2.core.client.ClientPanel;
import sanguosha2.core.client.ClientPanelUI;
import sanguosha2.net.client.Client;
import sanguosha2.net.client.ClientMessageListener;
import sanguosha2.ui.client.components.ControlButtonGui;

public class ConnectScreenGui extends JPanel implements ClientPanelUI, ClientPanel<ConnectScreenGui> {
	private static final long serialVersionUID = -948793753026200428L;
	private JButton connectButton;

	public ConnectScreenGui(Client client) {
		setLayout(new GridLayout(0, 1));
		this.connectButton = new ControlButtonGui("Connect", e -> {
			new Thread(() -> client.connect()).start();
		});
		add(connectButton);
	}

	@Override
	public ClientMessageListener getMessageListener() {
		return null;
	}

	@Override
	public ConnectScreenGui getContent() {
		return this;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}

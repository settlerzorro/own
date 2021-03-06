package sanguosha2.ui.game;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;

import sanguosha2.cards.Card;
import sanguosha2.core.Constants;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.original.Blank;
import sanguosha2.core.heroes.original.HeroOriginal;
import sanguosha2.core.player.PlayerCompleteClient;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.player.PlayerSimple;
import sanguosha2.listeners.game.GameListener;
import sanguosha2.ui.game.interfaces.CardRackUI;
import sanguosha2.ui.game.interfaces.ClientGameUI;
import sanguosha2.ui.game.interfaces.HeroUI;
import sanguosha2.ui.game.interfaces.PlayerUI;

public class GamePanelGui extends JPanel implements GameListener, ClientGameUI<HeroOriginal> {
	private static final long serialVersionUID = 2519723480954332278L;

	public static final int WIDTH = Constants.SCREEN_WIDTH / 4 * 3;
	public static final int HEIGHT = Constants.SCREEN_HEIGHT / 4 * 3;

	private CardRackGui cardRack;
	private EquipmentRackGui equipmentRack;
	private HeroGui heroGui;
	private LifebarGui healthGui;
	private CardDisposalGui disposalGui;
	private DelayedBarGui delayedGui;
	private JPanel customizedSelectionPane;

	private PlayerCompleteClient myself;
	private List<PlayerGui> otherPlayers;
	private ButtonGui confirm;
	private ButtonGui cancel;
	private ButtonGui end;

	private JLabel deckSize;
	private MessageBoxGui messageBox;
	
	private final GamePanel<HeroOriginal> panel;
	
	private int cancelSetCount;

	public GamePanelGui(PlayerInfo player, GamePanel<HeroOriginal> panel) {
		this.panel = panel;
		setLayout(null);
		myself = new PlayerCompleteClient(player.getName(), player.getPosition());

		cardRack = new CardRackGui(panel);
		equipmentRack = new EquipmentRackGui(panel);
		heroGui = new HeroGui(panel);
		healthGui = new LifebarGui();
		disposalGui = new CardDisposalGui(this);
		delayedGui = new DelayedBarGui();
		otherPlayers = new ArrayList<PlayerGui>();
		deckSize = new JLabel();
		deckSize.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		deckSize.setSize(100, 100);
		deckSize.setLocation(WIDTH - 100, PlayerGui.HEIGHT);
		delayedGui.setSize(120, 35);
		delayedGui.setLocation(WIDTH - 120, HEIGHT - CardRackGui.HEIGHT - 35);
		messageBox = new MessageBoxGui();
		messageBox.setLocation(equipmentRack.getWidth(), HEIGHT - cardRack.getHeight() - MessageBoxGui.HEIGHT);

		myself.registerGameListener(this);
		myself.registerCardOnHandListener(cardRack);
		myself.registerEquipmentListener(equipmentRack);
		myself.registerHealthListener(healthGui);
		myself.registerCardDisposalListener(disposalGui);
		myself.registerPlayerStatusListener(heroGui);
		myself.registerDelayedListener(delayedGui);
		myself.setHero(new Blank());// change in the future
		healthGui.onSetHealthLimit(myself.getHero().getHealthLimit()); // change in the future
		healthGui.onSetHealthCurrent(myself.getHero().getHealthLimit()); // change in the future
		heroGui.setPlayer(myself);
		confirm = new ButtonGui("Confirm", e -> panel.getCurrentOperation().onConfirmed());
		confirm.setLocation(0, HEIGHT - CardRackGui.HEIGHT - ButtonGui.HEIGHT);
		cancel = new ButtonGui("Cancel", e -> panel.getCurrentOperation().onCanceled());
		cancel.setLocation(ButtonGui.WIDTH, HEIGHT - CardRackGui.HEIGHT - ButtonGui.HEIGHT);
		end = new ButtonGui("End", e -> panel.getCurrentOperation().onEnded());
		end.setLocation(ButtonGui.WIDTH * 2, HEIGHT - CardRackGui.HEIGHT - ButtonGui.HEIGHT);
		
		cancelSetCount = 0;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		add(cardRack);
		add(equipmentRack);
		add(heroGui);
		add(healthGui);
		add(disposalGui);
		add(delayedGui);
		add(confirm);
		add(cancel);
		add(end);
		add(deckSize);
		add(messageBox);
	}

	public synchronized void addPlayer(PlayerInfo info) {
		PlayerSimple player = new PlayerSimple(info.getName(), info.getPosition());
		player.registerCardDisposalListener(disposalGui);
		PlayerGui p = new PlayerGui(player, panel);
		player.setHero(new Blank()); // remove later
		otherPlayers.add(p);
		p.setLocation(WIDTH - (otherPlayers.size()) * PlayerGui.WIDTH, 0);
		add(p);
		repaint();
	}

	@Override
	public CardRackUI getCardRackUI() {
		return cardRack;
	}
	
	@Override
	public EquipmentRackGui getEquipmentRackUI() {
		return this.equipmentRack;
	}

	@Override
	public HeroUI<HeroOriginal> getHeroUI() {
		return heroGui;
	}
	
	@Override
	public List<? extends PlayerUI> getOtherPlayersUI() {
		return otherPlayers;
	}
	
	@Override
	public List<PlayerSimple> getOtherPlayers() {
		return otherPlayers.stream().map(ui -> ui.getPlayer()).collect(Collectors.toList());
	}
	
	@Override
	public PlayerCompleteClient getSelf() {
		return myself;
	}
	
	@Override
	public int getNumberOfPlayers() {
		return otherPlayers.size() + 1;
	}
	
	@Override
	public int getNumberOfPlayersAlive() {
		int count = this.myself.isAlive() ? 1 : 0;
		for (PlayerGui player : this.otherPlayers) {
			if (player.getPlayer().isAlive()) {
				count++;
			}
		}
		return count;
	}
	
	@Override
	public synchronized PlayerUI getOtherPlayerUI(PlayerInfo other) {
		for (PlayerGui ui : otherPlayers) {
			if (ui.getPlayer().getPlayerInfo().equals(other)) {
				return ui;
			}
		}
		throw new RuntimeException("No Other player ui found");
	}
	
	@Override
	public synchronized PlayerUI getOtherPlayerUI(String name) {
		for (PlayerGui ui : otherPlayers) {
			if (ui.getPlayer().getPlayerInfo().getName().equals(name)) {
				return ui;
			}
		}
		throw new RuntimeException("No Other player ui found");
	}
	
	@Override
	public PlayerSimple getPlayer(String name) {
		for (PlayerGui ui : otherPlayers) {
			if (ui.getPlayer().getName().equals(name)) {
				return ui.getPlayer();
			}
		}
		return null; // throw later
	}

	@Override
	public void onPlayerAdded(PlayerSimple player) {
		player.registerCardDisposalListener(disposalGui);
		PlayerGui p = new PlayerGui(player, panel);
		otherPlayers.add(p);
		p.setLocation(WIDTH - (otherPlayers.size()) * PlayerGui.WIDTH, 0);
		add(p);
		repaint();
	}

	@Override
	public void setCardSelected(Card card, boolean selected) {
		cardRack.setCardSelected(card, selected);
	}

	@Override
	public void setTargetSelected(PlayerInfo player, boolean selected) {
		for (PlayerGui p : otherPlayers)
			if (p.getPlayer().equals(player)) {
				if (selected) {
					p.setActivated(true);
					heroGui.setActivated(true);
				} else {
					p.setActivated(false);
					heroGui.setActivated(false);
				}
				return;
			}

	}

	@Override
	public void setCardSelectable(Card card, boolean selectable) {
		cardRack.setCardSelectable(card, selectable);
	}

	@Override
	public void setTargetSelectable(PlayerInfo player, boolean selectable) {
		if (myself.equals(player))
			heroGui.setEnabled(selectable);
		for (PlayerGui p : otherPlayers)
			if (p.getPlayer().equals(player)) {
				p.setEnabled(selectable);
				return;
			}
	}

	@Override
	public void setConfirmEnabled(boolean isEnabled) {
		confirm.setEnabled(isEnabled);
	}

	@Override
	public void setCancelEnabled(boolean isEnabled) {
		if (isEnabled) {
			cancelSetCount++;
			cancel.setEnabled(true);
		} else {
			cancelSetCount--;
			if (cancelSetCount <= 0) {
				cancelSetCount = 0;
				cancel.setEnabled(false);
			}
		}
	}
	
	@Override
	public void setEndEnabled(boolean isEnabled) {
		end.setEnabled(isEnabled);
	}

	@Override
	public void setDeckSize(int size) {
		deckSize.setText(Integer.toString(size));
	}

	@Override
	public void setMessage(String message) {
		messageBox.setMessage(message);
	}

	@Override
	public void clearMessage() {
		messageBox.clearMessage();
	}

	@Override
	public void displayCustomizedSelectionPaneAtCenter(JPanel panel) {
		this.removeSelectionPane();
		customizedSelectionPane = panel;
		customizedSelectionPane.setLocation((WIDTH - customizedSelectionPane.getWidth()) / 2,
				(HEIGHT - CardRackGui.HEIGHT - customizedSelectionPane.getHeight()) / 2);
		add(customizedSelectionPane);
		setComponentZOrder(customizedSelectionPane, 0); // foremost
		customizedSelectionPane.validate();
		customizedSelectionPane.repaint();
	}
	
	@Override
	public JPanel getSelectionPane() {
		return this.customizedSelectionPane;
	}

	@Override
	public void removeSelectionPane() {
		if (this.customizedSelectionPane != null) {
			this.remove(this.customizedSelectionPane);
			this.customizedSelectionPane = null;
			this.validate();
			this.repaint();
		}
	}
	
	@Override
	public void showCountdownBar() {
		// TODO implement action bar for self
	}
	
	@Override
	public JPanel getPanel() {
		return this;
	}
}
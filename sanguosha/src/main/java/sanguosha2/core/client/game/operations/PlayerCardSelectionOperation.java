package sanguosha2.core.client.game.operations;

import java.util.Arrays;
import java.util.Collection;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.commands.game.server.ingame.PlayerCardSelectionInGameServerCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.core.player.PlayerSimple;
import sanguosha2.ui.game.custom.CardSelectionPane;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.CardUI;
import sanguosha2.ui.game.interfaces.EquipmentUI;

public class PlayerCardSelectionOperation implements Operation {
	
	private GamePanel<? extends Hero> panel;
	private final PlayerInfo target;
	private final Collection<PlayerCardZone> zones;
	private final Collection<EquipmentType> equipmentTypes;
	
	public PlayerCardSelectionOperation(
		PlayerInfo target,
		Collection<PlayerCardZone> zones,
		Collection<EquipmentType> equipmentTypes
	) {
		this.target = target;
		this.zones = zones;
		this.equipmentTypes = equipmentTypes != null ? equipmentTypes : Arrays.asList(EquipmentType.values());
	}

	@Override
	public void onCardClicked(CardUI card) {
		this.panel.getContent().removeSelectionPane();
		this.panel.popOperation();
		this.panel.getChannel().send(new PlayerCardSelectionInGameServerCommand(card.getCard(), PlayerCardZone.HAND));
	}
	
	@Override
	public void onEquipmentClicked(EquipmentUI equipment) {
		this.panel.getContent().removeSelectionPane();
		this.panel.popOperation();
		this.panel.getChannel().send(new PlayerCardSelectionInGameServerCommand(equipment.getEquipment(), PlayerCardZone.EQUIPMENT));
	}
	
	@Override
	public void onDelayedClicked(CardUI card) {
		this.panel.getContent().removeSelectionPane();
		this.panel.popOperation();
		this.panel.getChannel().send(new PlayerCardSelectionInGameServerCommand(card.getCard(), PlayerCardZone.DELAYED));
	}

	@Override
	public void onActivated(GamePanel<? extends Hero> panel, Activatable source) {
		this.panel = panel;
		PlayerSimple target = panel.getContent().getPlayer(this.target.getName());
		panel.getContent().displayCustomizedSelectionPaneAtCenter(new CardSelectionPane(target, this.zones, this.equipmentTypes, panel));
	}
}

package sanguosha2.commands.game.client;

import java.util.Collection;

import sanguosha2. cards.equipments.Equipment.EquipmentType;
import sanguosha2. core.client.game.operations.Operation;
import sanguosha2. core.client.game.operations.PlayerCardSelectionOperation;
import sanguosha2. core.player.PlayerCardZone;
import sanguosha2. core.player.PlayerInfo;

public class ShowCardSelectionPanelUIClientCommand extends AbstractSingleTargetOperationGameClientCommand {

	private static final long serialVersionUID = 1L;
	
	private final PlayerInfo selectionTarget;
	private final Collection<PlayerCardZone> zones;
	private final Collection<EquipmentType> equipmentTypes;
	
	public ShowCardSelectionPanelUIClientCommand(
		PlayerInfo source,
		PlayerInfo selectionTarget,
		Collection<PlayerCardZone> zones,
		Collection<EquipmentType> equipmentTypes
	) {
		super(source);
		this.selectionTarget = selectionTarget;
		this.zones = zones;
		this.equipmentTypes = equipmentTypes;
	}

	@Override
	protected Operation getOperation() {
		return new PlayerCardSelectionOperation(this.selectionTarget, this.zones, this.equipmentTypes);
	}

}

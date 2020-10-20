package sanguosha2.core.event.game.instants;

import java.util.Collection;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.event.game.basic.AbstractSingleTargetGameEvent;
import sanguosha2.core.player.PlayerCardZone;
import sanguosha2.core.player.PlayerInfo;

public class PlayerCardSelectionEvent extends AbstractSingleTargetGameEvent {
	
	private PlayerInfo source;
	private Collection<PlayerCardZone> zones;
	private Collection<EquipmentType> equipmentTypes;

	public PlayerCardSelectionEvent(PlayerInfo source, PlayerInfo target, Collection<PlayerCardZone> zones) {
		this(source, target, zones, null);
	}
	
	public PlayerCardSelectionEvent(
		PlayerInfo source,
		PlayerInfo target,
		Collection<PlayerCardZone> zones,
		Collection<EquipmentType> equipmentTypes
	) {
		super(target);
		this.source = source;
		this.zones = zones;
		this.equipmentTypes = equipmentTypes;
	}
	
	
	public PlayerInfo getSource() {
		return this.source;
	}
	
	public Collection<PlayerCardZone> getZones() {
		return this.zones;
	}
	
	public Collection<EquipmentType> getEquipmentTypes() {
		return this.equipmentTypes;
	}

}

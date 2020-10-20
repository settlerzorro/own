package sanguosha2.cards.equipments;

import sanguosha2.cards.Card;
import sanguosha2.core.GameState;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.client.game.operations.EquipOperation;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;

/**
 * The "Equipment" type of cards, consisting of all equipments
 * 
 * @author Harry
 *
 */
public abstract class Equipment extends Card {

	private static final long serialVersionUID = 5968539700238484665L;
	
	private EquipmentType equipmentType;// 1.Weapon 2.Shield 3.Horse+ 4.Horse-
	private boolean equipped;

	public enum EquipmentType {
		WEAPON, SHIELD, HORSEPLUS, HORSEMINUS
	}

	public Equipment(int num, Suit suit, EquipmentType equipmentType, int id) {
		super(num, suit, CardType.EQUIPMENT, id);
		this.equipped = false;
		this.equipmentType = equipmentType;
	}

	public void setEquipped(boolean b) {
		equipped = b;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	@Override
	public Operation generateOperation() {
		return new EquipOperation();
	}

	@Override
	public boolean isActivatable(GameState game) {
		return true;
	}
	
	public void onEquipped(Game game, PlayerCompleteServer owner) {
		// I really don't know whether this is "clean" as it involves game and server side stuff
	}
	
	public void onEquipped(GamePanel<Hero> panel) {
		
	}
	
	public void onUnequipped(Game game, PlayerCompleteServer owner) {
		// I really don't know whether this is "clean" as it involves game and server side stuff
	}
	
	public void onUnequipped(GamePanel<Hero> panel) {
		
	}
	
	/**
	 * 
	 * Called when the abilities of the equipment is (re)activated
	 * @param game
	 * @param owner
	 */
	public void onActivated(Game game, PlayerCompleteServer owner) {
		this.onEquipped(game, owner);
	}
	
	/**
	 * Called when the abilities of the equipment is suppressed
	 * @param game
	 * @param owner
	 */
	public void onDeactivated(Game game, PlayerCompleteServer owner) {
		this.onUnequipped(game, owner);
	}
	
}

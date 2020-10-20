package sanguosha2.cards.specials.instant;

import sanguosha2.cards.equipments.Equipment.EquipmentType;
import sanguosha2.core.GameState;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.client.game.operations.instants.BorrowSwordOperation;
import sanguosha2.core.player.PlayerSimple;

public class BorrowSword extends Instant {

	private static final long serialVersionUID = -8537939550303913600L;

	public BorrowSword(int num, Suit suit, int id) {
		super(num, suit, id);
	}

	@Override
	public String getName() {
		return "Borrow Sword";
	}

	@Override
	public Operation generateOperation() {
		return new BorrowSwordOperation();
	}

	@Override
	public boolean isActivatable(GameState game) {
		for (PlayerSimple player : game.getOtherPlayers()) {
			if (player.isEquipped(EquipmentType.WEAPON)) {
				return true;
			}
		}
		return false;
	}

}

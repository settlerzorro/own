package sanguosha2.core.server.game.controllers.equipment;

import sanguosha2.cards.basics.Attack;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.Damage.Element;
import sanguosha2.core.server.game.controllers.AbstractGameController;
import sanguosha2.core.server.game.controllers.AttackGameController;
import sanguosha2.core.server.game.controllers.interfaces.DecisionRequiredGameController;

public class FeatheredFanGameController extends AbstractGameController implements DecisionRequiredGameController {
	
	private final AttackGameController attackController;

	public FeatheredFanGameController(Game game, AttackGameController attackController) {
		super(game);
		this.attackController = attackController;
	}

	@Override
	public void proceed() {
		this.onUnloaded();
		this.game.getGameController().proceed();
	}

	@Override
	public void onDecisionMade(boolean confirmed) {
		if (confirmed) {
			Attack original = this.attackController.getAttackCard();
			this.attackController.setAttackCard(new Attack(Element.FIRE, original.getNumber(), original.getSuit()));
		}
	}

}

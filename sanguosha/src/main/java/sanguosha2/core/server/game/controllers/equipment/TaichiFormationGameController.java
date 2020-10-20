package sanguosha2.core.server.game.controllers.equipment;

import sanguosha2.cards.Card;
import sanguosha2.cards.Card.Color;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AbstractGameController;
import sanguosha2.core.server.game.controllers.ArbitrationController;
import sanguosha2.core.server.game.controllers.DodgeGameController;
import sanguosha2.core.server.game.controllers.GameController;
import sanguosha2.core.server.game.controllers.interfaces.ArbitrationRequiredGameController;
import sanguosha2.core.server.game.controllers.interfaces.DecisionRequiredGameController;

public class TaichiFormationGameController extends AbstractGameController implements DecisionRequiredGameController, ArbitrationRequiredGameController {
	
	private final PlayerCompleteServer target;
	private boolean effective;

	public TaichiFormationGameController(Game game, PlayerCompleteServer target) {
		super(game);
		this.target = target;
		this.effective = false;
	}

	@Override
	public void proceed() {
		this.onUnloaded();
		this.game.getGameController().proceed();
	}

	@Override
	public void onDecisionMade(boolean confirmed) {
		if (confirmed) {
			this.game.pushGameController(new ArbitrationController(this.game, this.target));
		}
	}

	@Override
	public void onArbitrationCompleted(Card card) {
		this.effective = card.getColor() == Color.RED;
	}
	
	@Override
	protected void onNextControllerLoaded(GameController controller) {
		if (this.effective) {
			((DodgeGameController) controller).onDodgeStageSkipped();
		} else {
			((DodgeGameController) controller).onDodgeStageNotSkipped();
		}
	}

}

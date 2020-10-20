package sanguosha2.core.server.game.controllers.specials.instants;

import sanguosha2.cards.Card;
import sanguosha2.cards.basics.Attack;
import sanguosha2.core.event.game.basic.RequestAttackEvent;
import sanguosha2.core.player.PlayerCompleteServer;
import sanguosha2.core.server.game.Game;
import sanguosha2.core.server.game.controllers.AbstractGameController;
import sanguosha2.core.server.game.controllers.AttackGameController;
import sanguosha2.core.server.game.controllers.interfaces.AttackUsableGameController;
import sanguosha2.exceptions.server.game.GameFlowInterruptedException;

public class DragonBladeGameController extends AbstractGameController implements AttackUsableGameController {
	
	private final PlayerCompleteServer source;
	private final PlayerCompleteServer target;
	private boolean actionTaken;

	public DragonBladeGameController(Game game, PlayerCompleteServer source, PlayerCompleteServer target) {
		super(game);
		this.source = source;
		this.target = target;
		this.actionTaken = false;
	}

	@Override
	public void proceed() {
		if (this.actionTaken) {
			this.onUnloaded();
			this.game.getGameController().proceed();
		} else {
			try {
				this.game.emit(new RequestAttackEvent(
					this.source.getPlayerInfo(),
					"Use Dragon Blade?"
				));
			} catch (GameFlowInterruptedException e) {
				e.resume();
			}
		}
	}

	@Override
	public void onAttackUsed(Card card) {
		this.actionTaken = true;
		this.game.pushGameController(new AttackGameController(this.source, this.target, (Attack) card, this.game));
	}

	@Override
	public void onAttackNotUsed() {
		this.actionTaken = true;
	}

}

package sanguosha2.commands.game.server.ingame;

import sanguosha2. cards.Card;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.GameController;
import sanguosha2. core.server.game.controllers.specials.instants.BorrowSwordGameController;

public class InitiateBorrowSwordInGameServerCommand extends AbstractInitiationInGameServerCommand {

	private static final long serialVersionUID = 1L;
	private final PlayerInfo attackTarget;

	public InitiateBorrowSwordInGameServerCommand(PlayerInfo target, PlayerInfo attackTarget, Card card) {
		super(target, card);
		this.attackTarget = attackTarget;
	}

	@Override
	protected GameController getController(Game game, PlayerInfo target) {
		return new BorrowSwordGameController(game.getCurrentPlayer().getPlayerInfo(), target, attackTarget, game);
	}

}

package sanguosha2.commands.game.server.ingame;

import java.util.Set;
import java.util.stream.Collectors;

import sanguosha2. cards.basics.Attack;
import sanguosha2. core.player.PlayerCompleteServer;
import sanguosha2. core.player.PlayerInfo;
import sanguosha2. core.server.game.Game;
import sanguosha2. core.server.game.controllers.AttackGameController;
import sanguosha2. exceptions.server.game.InvalidPlayerCommandException;

public class InitiateAttackInGameServerCommand extends InGameServerCommand {
	
	private static final long serialVersionUID = -4460787768760646177L;

	private final PlayerInfo source;
	private final Set<PlayerInfo> targets;
	private final Attack attack;
	
	public InitiateAttackInGameServerCommand(PlayerInfo source, Set<PlayerInfo> targets, Attack attack) {
		this.source = source;
		this.targets = targets;
		this.attack = attack;
	}

	@Override
	public void execute(Game game) {
		PlayerCompleteServer player = game.findPlayer(source);
		Set<PlayerCompleteServer> targets = this.targets.stream().map(target -> game.findPlayer(target)).collect(Collectors.toSet());
		try {
			player.useAttack(targets);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
			return;
		}
		if (attack != null) {
			try {
				player.useCard(attack);
			} catch (InvalidPlayerCommandException e) {
				try {
					player.setAttackUsed(player.getAttackUsed() - 1);
				} catch (InvalidPlayerCommandException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				return;
			}
		}
		game.pushGameController(new AttackGameController(player, targets, attack, game));
		game.getGameController().proceed();
	}

}

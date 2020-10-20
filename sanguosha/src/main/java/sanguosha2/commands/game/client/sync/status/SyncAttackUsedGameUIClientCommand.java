package sanguosha2.commands.game.client.sync.status;

import java.util.Set;
import java.util.stream.Collectors;

import sanguosha2.commands.game.client.AbstractGameUIClientCommand;
import sanguosha2.core.client.GamePanel;
import sanguosha2.core.heroes.Hero;
import sanguosha2.core.player.PlayerInfo;
import sanguosha2.exceptions.server.game.InvalidPlayerCommandException;

public class SyncAttackUsedGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = 7306926860771153864L;
	
	private final Set<PlayerInfo> targets;
	
	public SyncAttackUsedGameUIClientCommand(Set<PlayerInfo> targets) {
		this.targets = targets;
	}
	

	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		try {
			panel.getContent().getSelf().useAttack(
				this.targets.stream().map(info -> panel.getContent().getPlayer(info.getName())).collect(Collectors.toSet())
			);
		} catch (InvalidPlayerCommandException e) {
			e.printStackTrace();
		}
	}

}

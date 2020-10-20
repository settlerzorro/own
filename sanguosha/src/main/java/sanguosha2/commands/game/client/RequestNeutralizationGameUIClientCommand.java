package sanguosha2.commands.game.client;

import sanguosha2. core.client.GamePanel;
import sanguosha2. core.client.game.operations.instants.NeutralizationOperation;
import sanguosha2. core.heroes.Hero;

public class RequestNeutralizationGameUIClientCommand extends AbstractGameUIClientCommand {

	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	public RequestNeutralizationGameUIClientCommand(String message) {
		this.message = message;
	}
	
	@Override
	protected void execute(GamePanel<? extends Hero> panel) {
		panel.pushOperation(new NeutralizationOperation(this.message));
	}

}

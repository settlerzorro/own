package sanguosha2.core.client;

import sanguosha2.core.client.game.event.ClientGameEvent;
import sanguosha2.core.client.game.listener.ClientEventListener;
import sanguosha2.core.client.game.operations.Operation;
import sanguosha2.core.heroes.Hero;
import sanguosha2.net.Channel;
import sanguosha2.ui.game.interfaces.Activatable;
import sanguosha2.ui.game.interfaces.ClientGameUI;

public interface GamePanel<T extends Hero> extends ClientPanel<ClientGameUI<T>> {

	public void pushOperation(Operation operation, Activatable source);
	
	default public void pushOperation(Operation operation) {
		pushOperation(operation, null);
	}

	public void popOperation();
	
	public void registerEventListener(ClientEventListener<? extends ClientGameEvent, T> listener);
	
	public void removeEventListener(ClientEventListener<? extends ClientGameEvent, T> listener);
	
	public <E extends ClientGameEvent> void emit(E event);

	public Operation getCurrentOperation();

	public Channel getChannel();
	
}

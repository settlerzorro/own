package sanguosha2.commands.game.client;

import sanguosha2.commands.Command;
import sanguosha2.core.client.ClientFrame;
import sanguosha2.core.heroes.Hero;

public interface GameClientCommand<T extends Hero> extends Command<ClientFrame> {

}

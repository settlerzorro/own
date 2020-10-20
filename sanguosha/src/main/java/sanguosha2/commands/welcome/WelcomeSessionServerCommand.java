package sanguosha2.commands.welcome;

import sanguosha2. commands.ServerCommand;
import sanguosha2. core.server.WelcomeSession;

/**
 * This interface represents {@linkplain ServerCommand}s that will be executed
 * on a {@linkplain WelcomeSession} on server side.
 * 
 * @author Harry
 *
 */
public interface WelcomeSessionServerCommand extends ServerCommand<WelcomeSession> {}

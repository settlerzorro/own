package sanguosha2.net;

import sanguosha2.commands.Command;

public interface Channel {

	/**
	 * Send command to server / client
	 * 
	 * @param command : the command to be sent
	 */
	public void send(Command<?> command);
	
}

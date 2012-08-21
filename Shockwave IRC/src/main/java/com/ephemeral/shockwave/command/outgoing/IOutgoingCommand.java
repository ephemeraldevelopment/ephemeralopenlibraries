package com.ephemeral.shockwave.command.outgoing;

import com.ephemeral.shockwave.command.ICommand;

public interface IOutgoingCommand extends ICommand
{
	/**
	 * Called by the library in order to forge the individual pieces of a Message object
	 * into a single coherent string to be sent to the server to initial some response
	 * or action.
	 * 
	 * @return Forged String
	 */
	public String forgeOutgoing();
}

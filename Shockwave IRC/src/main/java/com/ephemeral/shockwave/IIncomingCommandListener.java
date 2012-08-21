package com.ephemeral.shockwave;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;

public interface IIncomingCommandListener
{
	public void receivedCommand(IIncomingCommand command);
}

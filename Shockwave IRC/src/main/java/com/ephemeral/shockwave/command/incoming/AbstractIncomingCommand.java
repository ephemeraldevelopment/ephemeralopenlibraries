package com.ephemeral.shockwave.command.incoming;

import com.ephemeral.shockwave.state.ClientState;

public abstract class AbstractIncomingCommand implements IIncomingCommand
{
	protected String rawCommand;

	@Override
	public String getRaw()
	{
		return rawCommand;
	}

	@Override
	public void setRaw(String rawMessage)
	{
		rawCommand = rawMessage;
	}
	
	@Override
	public boolean updateState(ClientState state)
	{
		return false;
	}
}

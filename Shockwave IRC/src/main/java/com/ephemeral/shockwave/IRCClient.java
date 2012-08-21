package com.ephemeral.shockwave;

import com.ephemeral.shockwave.state.ClientState;

public class IRCClient
{
	private ClientState state;
	private IRCConnection connection;
	
	
	public final ClientState getState()
	{
		return state;
	}
	
	public final IRCConnection getConnection()
	{
		return connection;
	}
	
}

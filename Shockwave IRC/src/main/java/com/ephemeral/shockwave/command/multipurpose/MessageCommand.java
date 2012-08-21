package com.ephemeral.shockwave.command.multipurpose;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;
import com.ephemeral.shockwave.command.outgoing.IOutgoingCommand;
import com.ephemeral.shockwave.state.ClientState;

public class MessageCommand implements IIncomingCommand, IOutgoingCommand
{
	private String target;
	private String message;
	
	public MessageCommand(){}
	
	public MessageCommand(String target, String message)
	{
		this.target = target;
		this.message = message;
	}
	
	
	@Override
	public String getIdentifier()
	{
		return "PRIVMSG";
	}

	@Override
	public String forgeOutgoing()
	{
		return String.format("%s %s :%s", getIdentifier(), target, message);
	}

	@Override
	public String getRaw()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRaw(String rawMessage)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateState(ClientState state)
	{
		return false;
	}

	@Override
	public IIncomingCommand parseFrom(String messagePrefix, String messageIdentifier, String messageParameters)
	{
		// TODO Auto-generated method stub
		return null;
	}

}

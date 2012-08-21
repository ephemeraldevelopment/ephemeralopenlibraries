package com.ephemeral.shockwave.command.incoming;

import com.ephemeral.shockwave.command.ICommand;
import com.ephemeral.shockwave.state.ClientState;

public interface IIncomingCommand extends ICommand
{
	/**
	 * Returns the raw string that this command was parsed from.
	 * 
	 * @return
	 */
	public String getRaw();
	
	
	/**
	 * Sets the raw string that this command was parsed from.
	 * 
	 * @param rawMessage
	 */
	public void setRaw(String rawMessage);
	
	public boolean updateState(ClientState state);
	
	/**
	 * Creates a properly formatted (or at least attempts to) incoming command from the supplied string depending upon
	 * 
	 * @param messagePrefix Usually some form of :<MESSAGE PREFIX> ....
	 * @param messageIdentifier Usually in the form of :<...> <MESSAGE IDENTIFIER> ....
	 * @param messageParameters Pretty much.. everything else.
	 * @return
	 */
	public IIncomingCommand parseFrom(String messagePrefix, String messageIdentifier, String messageParameters);
}

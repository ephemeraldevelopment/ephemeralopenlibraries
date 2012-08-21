package com.ephemeral.shockwave.command.incoming;


/**
 * Command couldn't be parsed, will be thrown as an error or sent to logger
 * somewhere along the line.
 * 
 * @author Mirage
 */
public class UnknownCommand extends AbstractIncomingCommand
{
	public UnknownCommand() {}
	public UnknownCommand(String rawCommand) { setRaw(rawCommand); }
	
	@Override
	public String getIdentifier()
	{
		return "UNKNOWN";
	}

	@Override
	public IIncomingCommand parseFrom(String messagePrefix, String messageIdentifier, String messageParameters)
	{
		return null;
	}
}

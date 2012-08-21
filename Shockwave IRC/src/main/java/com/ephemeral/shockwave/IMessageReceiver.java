package com.ephemeral.shockwave;

/**
 * Interface that represents any and all objects that the client can directly send a message to.
 * 
 * @author Mirage
 */
public interface IMessageReceiver
{
	/**
	 * Sends a PRIVMSG to the specified target depending on the object
	 * represented by it, usually either a channel or a member/user.
	 * 
	 * @param message
	 */
	public void sendMessage(String message);
}

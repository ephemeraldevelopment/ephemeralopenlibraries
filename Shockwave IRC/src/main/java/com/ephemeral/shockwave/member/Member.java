package com.ephemeral.shockwave.member;

import com.ephemeral.shockwave.IMessageReceiver;

/**
 * Represents a nick/user in a channel on the IRC network.
 * 
 * @author Mirage
 */
public class Member implements IMessageReceiver
{
	private Nick nick;
	private String modes = "";
	
	public String simpleNick()
	{
		
		// TODO: fix this, just a placeholder.
		return "";
	}

	public boolean hasMode(char mode)
	{
		return modes.indexOf(mode) != -1;
	}
	
	public boolean equals(String shortNick)
	{
		return simpleNick().equalsIgnoreCase(shortNick);
	}
	
	
	
	@Override
	public void sendMessage(String message)
	{
		// TODO Auto-generated method stub
		
	}

}

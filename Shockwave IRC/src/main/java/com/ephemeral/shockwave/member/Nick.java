package com.ephemeral.shockwave.member;

/**
 * Represents a fully-qualified nick to a user on the IRC network including
 * their host-mask.
 * 
 * @author Mirage
 */
public class Nick
{
	private String nick;
	private String hostmask = "";
	
	
	public Nick(String nickStr)
	{
		
	}
	
	public String getNick()
	{
		return nick;
	}
	
	public String getHostMask()
	{
		return hostmask;
	}
	
	
	@Override
	public String toString()
	{
		return nick;
	}
	
	public String fullyQualified()
	{	
		return hostmask == null || hostmask.equals("") ? nick : nick + "@" + hostmask;
	}
}

package com.ephemeral.shockwave;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;

public final class RawStringCommandParser
{
	/**
	 * Pattern allowing for the easy recognition and splitting of the arguments of received raw strings.
	 * 
	 * Zero Group -> Entire String
	 * First Group -> Is Complex? (Thats all I could think of at the time, the only thing that doesn't seem to have it is PING/PONG so those will be non-complex commands)
	 * Second Group -> Full Prefix (Or Source)
	 * Third Group -> Junk
	 * Fourth Group -> Display nick without terminating ! (I'll get to fixing it one day?)
	 * Fifth Group -> Junk
	 * Sixth Group -> 'user' of irc client
	 * Seventh Group -> host mask of user / message source if its from the server on connection or w/e.
	 * Eigth Group -> Command Code / Command String
	 * Ninth Group -> Command Target (Can be nick, or channel usually)
	 * Tenth GRoup -> Remaining Arguments or junk, depending upon command.
	 */
	public static final Pattern COMMAND_RECOGNITION_PATTERN = Pattern.compile("^(:)?((([a-zA-Z]+)!)?(~?([a-zA-Z]+)@)?([a-zA-Z0-9\\.-]+))? ([a-zA-Z]+|[0-9]{3}) ([#a-zA-Z]+) ?((.*))?");
	
	public static IIncomingCommand parseCommand(String rawString)
	{
		IIncomingCommand incoming = null;
		
		if(rawString.startsWith(":"))
		{
			Matcher m = getMatcherForCommandString(rawString);
			
			if(m.find())
			{
				
				
				System.out.println("Grp Count: " + m.groupCount());
				
				for (int i = 0; i < m.groupCount(); i++)
				{
					System.out.println("Grp " + i + " -> " + m.group(i));
				}
				
				
				return incoming;
			}
		}
		// 'simple' command
		else
		{
			System.out.println("simple command detected");
		}
			
		
		return incoming;
	}
	
	private static Matcher getMatcherForCommandString(String rString)
	{
		return COMMAND_RECOGNITION_PATTERN.matcher(rString);
	}
}

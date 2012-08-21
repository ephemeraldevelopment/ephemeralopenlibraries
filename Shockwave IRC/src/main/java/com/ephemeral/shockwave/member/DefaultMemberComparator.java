package com.ephemeral.shockwave.member;

import java.util.Comparator;

/**
 * Default member comparator for automatic ordering in channel objects and thensome
 * this comparator however only takes into account op, voice, and regular mode users
 * so if you're using it on a network where there are more modes that factor into the
 * equation then you should override this; its relatively simple so long as you follow 
 * the pattern laid out here.
 * 
 * @author Mirage
 */
public final class DefaultMemberComparator implements Comparator<Member>
{
	@Override
	public final int compare(Member arg0, Member arg1)
	{
		if(arg0.hasMode('o') && arg1.hasMode('o'))
			return arg0.simpleNick().compareToIgnoreCase(arg1.simpleNick());
		else if(arg0.hasMode('o') && !arg1.hasMode('o'))
			return -1;
		else if(!arg0.hasMode('o') && arg1.hasMode('o'))
			return 1;
			
		else if(arg0.hasMode('v') && arg1.hasMode('v'))
			return arg0.simpleNick().compareToIgnoreCase(arg1.simpleNick());
		else if(arg0.hasMode('v') && !arg1.hasMode('v'))
			return -1;
		else if(!arg0.hasMode('v') && arg1.hasMode('v'))
			return 1;
		
		return arg0.simpleNick().compareToIgnoreCase(arg1.simpleNick());
	}
}

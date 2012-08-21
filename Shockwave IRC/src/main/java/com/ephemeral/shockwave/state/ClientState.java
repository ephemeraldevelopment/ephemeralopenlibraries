package com.ephemeral.shockwave.state;

import java.util.Comparator;

import com.ephemeral.shockwave.member.Member;

public class ClientState
{
	private State currentState;
	
	
	public State getState() { return currentState; }
	public void setState(State newState) { currentState = newState; }
	
	/**
	 * Override this if you want to add your own member comparator that can compare
	 * upon something aside from ops/voice and then display nick.
	 * 
	 * @return Comparator to give order for channel's lists.
	 */
	public Comparator<Member> getMemberComparator()
	{
		return null;
	}
}

package com.ephemeral.shockwave.member;

public interface IMemberChangeListener
{
	public void onMemberNickChange(String oldNick, String newNick);
	public void onMemberModeChange(String oldModes, String newModes);
}

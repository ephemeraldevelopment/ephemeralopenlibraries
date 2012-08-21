package com.ephemeral.shockwave.channel;

import com.ephemeral.shockwave.member.Member;

public interface IChannelObserver
{
	public void onMemberChange(Member m, MemberChangeType mct);
	public void onMemberBanned(Member m);
	public void onMemberJoined(Member m);
	public void onMemberParted(Member m);
	public void onMessageReceived(Member source, String message);
	public void onMessageSend(String message);
}

package com.ephemeral.shockwave.channel;

import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ephemeral.shockwave.IMessageReceiver;
import com.ephemeral.shockwave.IRCClient;
import com.ephemeral.shockwave.command.multipurpose.MessageCommand;
import com.ephemeral.shockwave.member.DefaultMemberComparator;
import com.ephemeral.shockwave.member.Member;
import com.ephemeral.shockwave.util.SortedArrayList;

public class Channel implements IMessageReceiver
{
	protected String name;
	protected IRCClient client;
	
    protected final ReentrantReadWriteLock channelLock = new ReentrantReadWriteLock();
    protected final Lock chanReadLock = channelLock.readLock();
    protected final Lock chanWriteLock = channelLock.writeLock();
	protected SortedArrayList<Member> members;
	
	
	public Channel(String name, IRCClient client)
	{
		this(name, client, new DefaultMemberComparator());
	}
	
	public Channel(String name, IRCClient client, Comparator<? super Member> comparator)
	{
		this.name = name;
		this.client = client;
		members = new SortedArrayList<Member>(comparator);
	}
	
	
	public void addMember(Member m)
	{
		chanWriteLock.lock();
		
		try
		{
			members.add(m);
		}
		finally
		{
			chanWriteLock.unlock();
		}
	}
	
	public void removeMember(Member m)
	{
		chanWriteLock.lock();
		
		try
		{
			members.remove(m);
		}
		finally
		{
			chanWriteLock.unlock();
		}
	}
	
	public Member memberAt(int index)
	{
		chanReadLock.lock();
		
		try
		{
			return members.get(index);
		}
		finally
		{
			chanReadLock.unlock();
		}	
	}

	@Override
	public void sendMessage(String message)
	{
		client.getConnection().sendCommand(new MessageCommand(name, message));
	}

}

package com.ephemeral.shockwave;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;

public final class MessageRegistrar
{
	private static volatile MessageRegistrar instance;
	
    private final ReentrantReadWriteLock registrationLock = new ReentrantReadWriteLock();
    private final Lock regReadLock = registrationLock.readLock();
    private final Lock regWriteLock = registrationLock.writeLock();
	
	private Map<String, IIncomingCommand> registeredMessageTypes = new HashMap<String, IIncomingCommand>();
	
	private MessageRegistrar()
	{
		// TODO: Add messages here.
	}
	
	
	public void registerMessageType(IIncomingCommand messageDefinition)
	{	
		regWriteLock.lock();
		
		try
		{
			registeredMessageTypes.put(messageDefinition.getIdentifier(), messageDefinition);
		}
		finally
		{
			regWriteLock.unlock();
		}
	}
	
	public IIncomingCommand getMessageType(String messageIdentifier)
	{
		regReadLock.lock();
		
		try
		{
			return registeredMessageTypes.get(messageIdentifier);
		}
		finally
		{
			regReadLock.unlock();
		}
	}
	
	public static MessageRegistrar getInstance()
	{
		if(instance == null)
			instance = new MessageRegistrar();
		
		return instance;
	}
}

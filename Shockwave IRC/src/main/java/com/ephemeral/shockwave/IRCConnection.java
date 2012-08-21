package com.ephemeral.shockwave;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;
import com.ephemeral.shockwave.command.outgoing.IOutgoingCommand;
import com.ephemeral.shockwave.state.ClientState;
import com.ephemeral.shockwave.state.State;

public final class IRCConnection
{
	private ClientState state;
	
    private final ReentrantReadWriteLock commandListenerLock = new ReentrantReadWriteLock();
    private final Lock cmdReadLock = commandListenerLock.readLock();
    private final Lock cmdWriteLock = commandListenerLock.writeLock();
	private Map<Class<? extends IIncomingCommand>, List<IIncomingCommandListener>> commandListeners;
	
	
	private Map<Class<? extends IIncomingCommand>, List<IStateChangeListener>> stateListeners;
	
	public IRCConnection()
	{
		this(new ClientState());
	}
	public IRCConnection(ClientState cState)
	{
		state = cState;
		commandListeners = new HashMap<Class<? extends IIncomingCommand>, List<IIncomingCommandListener>>();
		stateListeners = new HashMap<Class<? extends IIncomingCommand>, List<IStateChangeListener>>();
	}
	
	
	public void notifyCommandListeners(final IIncomingCommand command)
	{
		cmdReadLock.lock();
		
		try
		{
			if(commandListeners.containsKey(command.getClass()))
			{
				for (IIncomingCommandListener listener : commandListeners.get(command.getClass()))
				{
					listener.receivedCommand(command);
				}
			}
			
			if(stateListeners.containsKey(command.getClass()))
			{
				State prevState = state.getState();
				
				if(command.updateState(state))
				{
					for (IStateChangeListener listener : stateListeners.get(command.getClass()))
					{
						listener.stateChanged(command, prevState, state.getState());
					}
				}
			}
			
		}
		finally
		{
			cmdReadLock.unlock();
		}
	}
	
	@SafeVarargs
	public final void registerCommandListener(IIncomingCommandListener listener, Class<? extends IIncomingCommand>... forClasses)
	{
		cmdWriteLock.lock();
		
		try
		{
			for (Class<? extends IIncomingCommand> registrantClass : forClasses)
			{
				if(commandListeners.containsKey(registrantClass))
					commandListeners.get(registrantClass).add(listener);
				else
				{
					List<IIncomingCommandListener> tempList = new ArrayList<IIncomingCommandListener>();
					tempList.add(listener);
					
					commandListeners.put(registrantClass, tempList);
				}
			}
		}
		finally
		{
			cmdWriteLock.unlock();
		}
	}
	
	public void sendCommand(IOutgoingCommand command)
	{
		
	}
}

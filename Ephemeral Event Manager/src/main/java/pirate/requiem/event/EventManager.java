package pirate.requiem.event;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import pirate.requiem.event.exception.HandlerNotAnnotatedException;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public final class EventManager
{
    private static final ReentrantReadWriteLock fLock = new ReentrantReadWriteLock();
    private static final Lock fReadLock = fLock.readLock();
    private static final Lock fWriteLock = fLock.writeLock();
    
    private static Multimap<String, IEventHandler> eventHandlers = HashMultimap.create();

    public static void addHandler(IEventHandler handler)
    {
    	HandledEvents hEvt = handler.getClass().getAnnotation(HandledEvents.class);
    	
    	if(hEvt == null)
    		throw new HandlerNotAnnotatedException("Handler for class " + handler.getClass().getName() + " was not properly annotated and cannot be loaded.");
    
    	fWriteLock.lock();
    	
    	for (String currentEventName : hEvt.handledEvents())
    	{
    		eventHandlers.put(currentEventName, handler);
    	}
    	
    	fWriteLock.unlock();
    }
    
    public static void removeHandler(IEventHandler handler)
    {
    	HandledEvents hEvt = handler.getClass().getAnnotation(HandledEvents.class);
    	
    	if(hEvt == null)
    		throw new HandlerNotAnnotatedException("Handler for class " + handler.getClass().getName() + " was not properly annotated and cannot be unloaded.");
    
    	fWriteLock.lock();
    	
    	for (String currentEventName : hEvt.handledEvents())
    	{
    		eventHandlers.remove(currentEventName, handler);
    	}
    	
    	fWriteLock.unlock();
    }
    
    /**
     * Static method to notify the program of an event and let registered handlers for that event do their work.
     * 
     * Used for events that don't care to give arguments or source.
     * 
     * @param evt 
     * Event, from derived classes usually is just a string.
     */
    public static void Notify(final Event evt)
    {
    	Notify(evt, null, null);
    }
    
    /**
     * Static method to notify the program of an event and let registered handlers for that event do their work.
     * 
     * Used for events that don't care to give arguments
     * 
     * @param evt 
     * Event, from derived classes usually is just a string.
     * 
     * @param src
     * Event source
     */
    public static void Notify(final Event evt, final Object src)
    {
    	Notify(evt, src, null);
    }
    
    /**
     * Static method to notify the program of an event and let registered handlers for that event do their work.
     * 
     * Used for events that don't care to give their source.
     * 
     * @param evt 
     * Event, from derived classes usually is just a string.
     * 
     * @param args
     * Event arguments, can be derived.
     */
    public static void Notify(final Event evt, final EventArguments args)
    {
    	Notify(evt, null, args);
    }
 
    /**
     * Static method to notify the program of an event and let registered handlers for that event do their work.
     * 
     * @param evt 
     * Event, from derived classes usually is just a string.
     * 
     * @param src
     * Source of the event.
     * 
     * @param args
     * Event arguments, can be derived.
     */
    public static void Notify(final Event evt, final Object src, final EventArguments args)
    {
    	fReadLock.lock();
    	
    	for (final IEventHandler currentHandler : eventHandlers.get(evt.getName()))
		{
        	new Thread
        	(
        		new Runnable()
        		{
    				@Override
    				public void run()
    				{
    					currentHandler.handleEvent(evt, src, args);
    				}
        		}
        	).start();
		}
    	
    	fReadLock.unlock();
    }
    
}

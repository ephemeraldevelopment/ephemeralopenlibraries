package pirate.requiem.event.exception;

@SuppressWarnings("serial")
public class HandlerNotAnnotatedException extends RuntimeException
{
	public HandlerNotAnnotatedException()
	{
		this("Event Handler was not properly annotated.");
	}
	
	public HandlerNotAnnotatedException(String reason)
	{
		super(reason);
	}
}

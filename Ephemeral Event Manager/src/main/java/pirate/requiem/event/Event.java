package pirate.requiem.event;

public abstract class Event
{
	protected String eventName;
	
	public Event(String name)
	{
		eventName = name;
	}
	
	public String getName()
	{
		return eventName;
	}
	
	public String toString()
	{
		return eventName;
	}
}

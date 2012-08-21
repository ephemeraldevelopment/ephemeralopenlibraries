package pirate.requiem.event;

public interface IEventHandler
{
	public void handleEvent(Event evt, Object src, EventArguments args);
}

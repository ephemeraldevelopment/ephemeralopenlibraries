package com.ephemeral.shockwave;

import com.ephemeral.shockwave.command.incoming.IIncomingCommand;
import com.ephemeral.shockwave.state.State;

public interface IStateChangeListener
{
	public void stateChanged(IIncomingCommand source, State oldState, State newState);
}

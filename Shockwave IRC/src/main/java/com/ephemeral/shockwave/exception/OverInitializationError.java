package com.ephemeral.shockwave.exception;

@SuppressWarnings("serial")
public class OverInitializationError extends RuntimeException
{
	public OverInitializationError()
	{
		super("Class cannot be initialized more than once.");
	}
}

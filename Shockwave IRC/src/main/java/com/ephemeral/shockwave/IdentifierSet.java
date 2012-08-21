package com.ephemeral.shockwave;

public final class IdentifierSet
{
	private String stringIdentifier;
	private int numericIdentifier;
	
	public IdentifierSet(String strIdentifier, int numIdentifier)
	{
		stringIdentifier = strIdentifier;
		numericIdentifier = numIdentifier;
	}
	
	public String getStringIdent()
	{
		return stringIdentifier;
	}
	public int getNumericIdent()
	{
		return numericIdentifier;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj instanceof String)
			return equals((String) obj);
		else if(obj instanceof Integer)
			return equals((Integer) obj);
		else if(obj instanceof IdentifierSet)
			return equals((IdentifierSet) obj);
		else return super.equals(obj);
	}
	
	public boolean equals(IdentifierSet id)
	{
		return equals(id.getStringIdent()) || equals(id.getNumericIdent());
	}
	
	public boolean equals(String str)
	{
		return getStringIdent().equals(str);
	}
	
	public boolean equals(Integer i)
	{
		return i.intValue() == getNumericIdent();
	}
}

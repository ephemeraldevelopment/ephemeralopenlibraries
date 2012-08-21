package com.ephemeral.shockwave.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Extended functionality ArrayList that is sorted upon add operations that modify the 
 * list in any way, since I didn't care to go and binary sort for the operation to find 
 * insertion points it simply uses Collections.sort(...) to sort whenever its changed.
 * 
 * @author Mirage
 *
 * @param <E>
 */
@SuppressWarnings("serial")
public class SortedArrayList<E> extends ArrayList<E>
{
	private Comparator<? super E> comparator;
	
	public SortedArrayList(Comparator<? super E> comparator)
	{
		this.comparator = comparator;
	}
	
	@Override
	public boolean add(E e) 
	{
		boolean retVal = super.add(e);
		
		if(retVal)
			Collections.sort(this, comparator);
		
		return retVal;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		boolean retVal = super.addAll(c);
		
		if(retVal)
			Collections.sort(this, comparator);
		
		return retVal;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		boolean retVal = super.addAll(index, c);
		
		if(retVal)
			Collections.sort(this, comparator);
		
		return retVal;
	}
}

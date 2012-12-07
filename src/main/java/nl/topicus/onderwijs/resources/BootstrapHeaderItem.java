package nl.topicus.onderwijs.resources;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.PriorityHeaderItem;

public class BootstrapHeaderItem
{
	private BootstrapHeaderItem()
	{
	}

	public static HeaderItem get()
	{
		return new PriorityHeaderItem(CssHeaderItem.forReference(BootstrapCssReference.get()));
	}
}

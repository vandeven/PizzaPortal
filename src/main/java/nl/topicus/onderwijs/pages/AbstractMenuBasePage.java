package nl.topicus.onderwijs.pages;

import nl.topicus.onderwijs.panels.menu.MenuPanel;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AbstractMenuBasePage extends AbstractBasePage
{

	private static final long serialVersionUID = 1L;

	public AbstractMenuBasePage()
	{
		this(null);
	}

	public AbstractMenuBasePage(PageParameters parameters)
	{
		add(new MenuPanel("menu"));
	}

}
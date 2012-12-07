package nl.topicus.onderwijs;

import nl.topicus.onderwijs.panels.LoginPanel;

import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends AbstractBasePage
{
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		add(new LoginPanel("loginPanel"));
	}
}

package nl.topicus.onderwijs.pages;

import nl.topicus.onderwijs.pages.evenement.EvenementenPage;
import nl.topicus.onderwijs.panels.LoginPanel;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends AbstractBasePage
{
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters)
	{
		Subject user = SecurityUtils.getSubject();
		if (user.isAuthenticated())
		{
			throw new RestartResponseAtInterceptPageException(EvenementenPage.class, parameters);
		}
		add(new LoginPanel("loginPanel"));
	}
}

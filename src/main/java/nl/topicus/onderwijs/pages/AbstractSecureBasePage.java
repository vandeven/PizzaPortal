package nl.topicus.onderwijs.pages;

import nl.topicus.onderwijs.panels.menu.MenuPanel;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.RestartResponseException;

public abstract class AbstractSecureBasePage extends AbstractBasePage
{

	private static final long serialVersionUID = 1L;

	public AbstractSecureBasePage()
	{
		Subject user = SecurityUtils.getSubject();
		if (!user.isAuthenticated())
		{
			throw new RestartResponseException(HomePage.class);
		}

		add(new MenuPanel("menu"));
	}

}

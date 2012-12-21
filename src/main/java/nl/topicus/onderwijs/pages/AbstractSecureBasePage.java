package nl.topicus.onderwijs.pages;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class AbstractSecureBasePage extends AbstractMenuBasePage
{

	private static final long serialVersionUID = 1L;

	public AbstractSecureBasePage()
	{
		this(null);
	}

	public AbstractSecureBasePage(PageParameters parameters)
	{
		Subject user = SecurityUtils.getSubject();
		if (!user.isAuthenticated())
		{
			throw new RestartResponseAtInterceptPageException(HomePage.class, parameters);
		}
	}

}

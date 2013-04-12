package nl.topicus.onderwijs;

import nl.topicus.onderwijs.pages.HomePage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValueConversionException;

public abstract class ParameterDecoder
{
	protected static void isAuthenticated(PageParameters parameters)
	{
		Subject user = SecurityUtils.getSubject();
		if (!user.isAuthenticated())
		{
			throw new RestartResponseAtInterceptPageException(HomePage.class, parameters);
		}
	}

	protected static void checkHasValue(PageParameters parameters, String key)
	{
		if (parameters.get(key) == null || parameters.get(key).isEmpty())
		{
			throw new RestartResponseException(HomePage.class);
		}
	}

	protected static Long getLongValue(PageParameters parameters, String key)
	{
		try
		{
			return parameters.get(key).toLongObject();
		}
		catch (StringValueConversionException e)
		{
			return null;
		}
	}
}

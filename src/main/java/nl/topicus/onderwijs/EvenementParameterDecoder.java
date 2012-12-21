package nl.topicus.onderwijs;

import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.HomePage;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EvenementParameterDecoder extends ParameterDecoder
{
	private static final String eventId = "evenementid";

	public static String getPath()
	{
		return "/evenement/${" + eventId + "}";
	}

	public static Evenement decodeParameters(PageParameters parameters)
	{
		isAuthenticated(parameters);
		checkHasValue(parameters, eventId);

		Long nummer = getLongValue(parameters, eventId, eventId);
		if (nummer != null)
		{
			return null;
		}
		throw new RestartResponseException(HomePage.class);
	}
}

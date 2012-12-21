package nl.topicus.onderwijs.providers;

import javax.enterprise.context.ApplicationScoped;

import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

@ApplicationScoped
public class EvenementProvider extends AbstractPersistenceProvider<Evenement, EvenementZoekFilter>
{

	@Override
	protected String createQuery(EvenementZoekFilter filter)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("select e from Evenement e");
		return builder.toString();
	}

	@Override
	protected String createCountQuery(EvenementZoekFilter filter)
	{
		return "select count(e) from Evenement e";
	}

}

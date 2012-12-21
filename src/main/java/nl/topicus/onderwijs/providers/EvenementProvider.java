package nl.topicus.onderwijs.providers;

import javax.enterprise.context.ApplicationScoped;

import nl.topicus.onderwijs.dao.filters.AbstractZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

@ApplicationScoped
public class EvenementProvider extends AbstractPersistenceProvider<Evenement>
{

	@Override
	protected String createQuery(AbstractZoekFilter<Evenement> filter)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("select e from Evenement e");
		return builder.toString();
	}

	@Override
	protected String createCountQuery(AbstractZoekFilter<Evenement> filter)
	{
		return "select count(e) from Evenement e";
	}

}

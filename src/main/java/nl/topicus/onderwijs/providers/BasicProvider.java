package nl.topicus.onderwijs.providers;

import javax.enterprise.context.Dependent;

import nl.topicus.cobra.entities.IdObject;
import nl.topicus.onderwijs.dao.filters.AbstractZoekFilter;

@Dependent
public class BasicProvider<T extends IdObject> extends AbstractPersistenceProvider<T>
{

	@Override
	protected String createQuery(AbstractZoekFilter<T> filter)
	{
		return null;
	}

	@Override
	protected String createCountQuery(AbstractZoekFilter<T> filter)
	{
		return null;
	}

}

package nl.topicus.onderwijs.providers;

import javax.enterprise.context.Dependent;

import nl.topicus.cobra.entities.IdObject;
import nl.topicus.onderwijs.dao.filters.IdObjectZoekFilter;

@Dependent
public class IdObjectProvider<T extends IdObject> extends
		AbstractPersistenceProvider<T, IdObjectZoekFilter<T>>
{

	@Override
	protected String createQuery(IdObjectZoekFilter<T> filter)
	{
		return null;
	}

	@Override
	protected String createCountQuery(IdObjectZoekFilter<T> filter)
	{
		return null;
	}

}

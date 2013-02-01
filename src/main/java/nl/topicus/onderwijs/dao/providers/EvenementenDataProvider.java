package nl.topicus.onderwijs.dao.providers;

import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.providers.AbstractPersistenceProvider;
import nl.topicus.onderwijs.providers.EvenementProvider;

public class EvenementenDataProvider extends AbstractDataProvider<Evenement, EvenementZoekFilter>
{

	private static final long serialVersionUID = 1L;

	public EvenementenDataProvider(EvenementZoekFilter filter)
	{
		super(filter);
	}

	@Override
	protected AbstractPersistenceProvider<Evenement, EvenementZoekFilter> getProvider()
	{
		return new EvenementProvider();
	}

}

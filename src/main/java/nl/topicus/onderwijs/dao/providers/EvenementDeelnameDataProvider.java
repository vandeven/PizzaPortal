package nl.topicus.onderwijs.dao.providers;

import nl.topicus.onderwijs.dao.filters.EvenementDeelnameZoekFilter;
import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.providers.AbstractPersistenceProvider;
import nl.topicus.onderwijs.providers.EvenementDeelnameProvider;

public class EvenementDeelnameDataProvider extends
		AbstractDataProvider<EvenementDeelname, EvenementDeelnameZoekFilter>
{

	private static final long serialVersionUID = 1L;

	public EvenementDeelnameDataProvider(EvenementDeelnameZoekFilter filter)
	{
		super(filter);
	}

	@Override
	protected AbstractPersistenceProvider<EvenementDeelname, EvenementDeelnameZoekFilter> getProvider()
	{
		return new EvenementDeelnameProvider();
	}
}

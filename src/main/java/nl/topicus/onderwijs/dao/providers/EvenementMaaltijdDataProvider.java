package nl.topicus.onderwijs.dao.providers;

import nl.topicus.onderwijs.dao.filters.EvenementMaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.EvenementMaaltijd;
import nl.topicus.onderwijs.providers.AbstractPersistenceProvider;
import nl.topicus.onderwijs.providers.EvenementMaaltijdProvider;

public class EvenementMaaltijdDataProvider extends
		AbstractDataProvider<EvenementMaaltijd, EvenementMaaltijdZoekFilter>
{

	private static final long serialVersionUID = 1L;

	public EvenementMaaltijdDataProvider(EvenementMaaltijdZoekFilter filter)
	{
		super(filter);
	}

	@Override
	protected AbstractPersistenceProvider<EvenementMaaltijd, EvenementMaaltijdZoekFilter> getProvider()
	{
		return new EvenementMaaltijdProvider();
	}

}

package nl.topicus.onderwijs.dao.providers;

import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.Maaltijd;
import nl.topicus.onderwijs.providers.AbstractPersistenceProvider;
import nl.topicus.onderwijs.providers.MaaltijdProvider;

public class MaaltijdDataProvider extends AbstractDataProvider<Maaltijd, MaaltijdZoekFilter>
{

	private static final long serialVersionUID = 1L;

	public MaaltijdDataProvider(MaaltijdZoekFilter filter)
	{
		super(filter);
	}

	@Override
	protected AbstractPersistenceProvider<Maaltijd, MaaltijdZoekFilter> getProvider()
	{
		return new MaaltijdProvider();
	}

}

package nl.topicus.onderwijs.providers;

import nl.topicus.onderwijs.dao.filters.AbstractZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account>
{
	@Override
	protected String createQuery(AbstractZoekFilter<Account> filter)
	{
		return "select m from Account m";
	}

	@Override
	protected String createCountQuery(AbstractZoekFilter<Account> filter)
	{
		return "select count(m) from Account m";
	}
}

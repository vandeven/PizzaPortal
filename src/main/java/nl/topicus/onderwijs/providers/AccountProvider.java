package nl.topicus.onderwijs.providers;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account, AccountZoekFilter>
{
	@Override
	protected String createQuery(AccountZoekFilter filter)
	{
		return "select m from Account m";
	}

	@Override
	protected String createCountQuery(AccountZoekFilter filter)
	{
		return "select count(m) from Account m";
	}
}

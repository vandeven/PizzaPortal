package nl.topicus.onderwijs.dao.impl;

import nl.topicus.cobra.dao.hibernate.AbstractZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

import org.hibernate.Criteria;

public class AccountDataAccessHelper extends
		AbstractZoekFilterDataAccessHelper<Account, AccountZoekFilter> implements
		nl.topicus.onderwijs.dao.helpers.AccountDataAccessHelper
{

	@Override
	protected Criteria createCriteria(AccountZoekFilter filter)
	{
		return null;
	}

}

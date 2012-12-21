package nl.topicus.onderwijs.providers;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account, AccountZoekFilter>
{

	@Override
	protected Predicate createWhere(Root<Account> root, CriteriaBuilder builder,
			AccountZoekFilter filter)
	{
		Predicate predicate = builder.equal(root.get("gebruikersnaam"), filter.getGebruikersnaam());
		return predicate;
	}

	@Override
	protected Class<Account> getEntityClass()
	{
		return Account.class;
	}

}

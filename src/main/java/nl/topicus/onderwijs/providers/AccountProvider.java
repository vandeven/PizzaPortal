package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account, AccountZoekFilter>
{

	@Override
	protected List<Predicate> createWhere(Root<Account> root, CriteriaBuilder cb,
			AccountZoekFilter filter)
	{
		PredicateBuilder builder = new PredicateBuilder(root, cb);

		if (filter.getGebruikersnaam() != null)
		{
			builder.addEq("gebruikersnaam", filter.getGebruikersnaam());
		}
		return builder.build();
	}

	@Override
	protected Class<Account> getEntityClass()
	{
		return Account.class;
	}

}

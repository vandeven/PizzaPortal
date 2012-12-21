package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

import com.google.common.collect.Lists;

public class AccountProvider extends AbstractPersistenceProvider<Account, AccountZoekFilter>
{

	@Override
	protected List<Predicate> createWhere(Root<Account> root, CriteriaBuilder builder,
			AccountZoekFilter filter)
	{
		List<Predicate> predicates = Lists.newArrayList();
		if (filter.getGebruikersnaam() != null)
		{
			predicates.add(builder.equal(root.get("gebruikersnaam"), filter.getGebruikersnaam()));
		}
		return predicates;
	}

	@Override
	protected Class<Account> getEntityClass()
	{
		return Account.class;
	}

}

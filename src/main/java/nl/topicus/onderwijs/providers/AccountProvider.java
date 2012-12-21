package nl.topicus.onderwijs.providers;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account, AccountZoekFilter>
{
	@Override
	protected CriteriaBuilder createCriteria(AccountZoekFilter filter)
	{
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> root = cq.from(Account.class);
		Predicate predicate = cb.conjunction();

		cb.and(predicate, cb.equal(root.get()), filter.getGebruikersnaam());

		cq.select(root).where(predicate);
		return cb;
	}
}

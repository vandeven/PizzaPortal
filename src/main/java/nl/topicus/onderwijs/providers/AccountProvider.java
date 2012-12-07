package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.Query;

import nl.topicus.onderwijs.entities.Account;

public class AccountProvider extends AbstractPersistenceProvider<Account>
{
	@SuppressWarnings("unchecked")
	public List<Account> getAccounts()
	{
		List<Account> list = null;
		begin();
		Query q = createQuery("select m from Account m");
		list = q.getResultList();
		end();

		return list;
	}
}

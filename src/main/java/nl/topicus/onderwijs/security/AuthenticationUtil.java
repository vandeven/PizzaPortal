package nl.topicus.onderwijs.security;

import java.util.List;

import nl.topicus.onderwijs.PizzaSession;
import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.models.ELModelFactory;
import nl.topicus.onderwijs.providers.AccountProvider;

public class AuthenticationUtil
{
	public static void loggedOn(AccountProvider accProvider, String username) throws Exception
	{
		Account acc = null;
		AccountZoekFilter filter = new AccountZoekFilter();
		filter.setGebruikersnaam(username);

		List<Account> accounts = accProvider.list(filter);

		if (accounts.isEmpty())
		{
			acc = new Account();
			acc.setGebruikersnaam(username);
			acc.saveAndCommit();
		}
		else if (accounts.size() == 1)
		{
			acc = accounts.get(0);
		}
		else
		{
			throw new Exception("Unknown login error.");
		}

		// Set session
		PizzaSession.get().setAccount(ELModelFactory.getModel(acc));
	}
}

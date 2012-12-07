package nl.topicus.onderwijs.dao.helpers;

import nl.topicus.cobra.dao.helpers.BatchZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;

public interface AccountDataAccessHelper extends
		BatchZoekFilterDataAccessHelper<Account, AccountZoekFilter>
{

}

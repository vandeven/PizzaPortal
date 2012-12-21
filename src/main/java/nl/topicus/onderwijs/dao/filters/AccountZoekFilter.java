package nl.topicus.onderwijs.dao.filters;

import nl.topicus.onderwijs.entities.Account;

public class AccountZoekFilter extends AbstractZoekFilter<Account>
{
	private static final long serialVersionUID = 1L;

	private String gebruikersnaam;

	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

}

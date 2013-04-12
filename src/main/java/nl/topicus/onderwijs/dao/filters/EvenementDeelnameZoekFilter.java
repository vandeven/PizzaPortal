package nl.topicus.onderwijs.dao.filters;

import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.entities.EvenementDeelname;

import org.apache.wicket.model.IModel;

public class EvenementDeelnameZoekFilter extends AbstractZoekFilter<EvenementDeelname>
{

	private static final long serialVersionUID = 1L;

	private IModel<Account> account;

	private IModel<Evenement> evenement;

	public IModel<Account> getAccount()
	{
		return account;
	}

	public void setAccount(IModel<Account> account)
	{
		this.account = account;
	}

	public IModel<Evenement> getEvenement()
	{
		return evenement;
	}

	public void setEvenement(IModel<Evenement> evenement)
	{
		this.evenement = evenement;
	}

}

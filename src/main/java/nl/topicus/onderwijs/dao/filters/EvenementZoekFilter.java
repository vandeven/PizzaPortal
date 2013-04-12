package nl.topicus.onderwijs.dao.filters;

import java.util.Date;

import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.entities.Evenement;

import org.apache.wicket.model.IModel;

public class EvenementZoekFilter extends AbstractZoekFilter<Evenement>
{

	private static final long serialVersionUID = 1L;

	private IModel<Account> account;

	private String naam;

	private Date voorDatum;

	private Date opOfNaDatum;

	private Date datum;

	public String getNaam()
	{
		return naam;
	}

	public void setNaam(String naam)
	{
		this.naam = naam;
	}

	public Date getDatum()
	{
		return datum;
	}

	public void setDatum(Date datum)
	{
		this.datum = datum;
	}

	public Date getVoorDatum()
	{
		return voorDatum;
	}

	public void setVoorDatum(Date voorDatum)
	{
		this.voorDatum = voorDatum;
	}

	public Date getOpOfNaDatum()
	{
		return opOfNaDatum;
	}

	public void setOpOfNaDatum(Date opOfNaDatum)
	{
		this.opOfNaDatum = opOfNaDatum;
	}

	public IModel<Account> getAccount()
	{
		return account;
	}

	public void setAccount(IModel<Account> account)
	{
		this.account = account;
	}
}

package nl.topicus.onderwijs.dao.filters;

import java.util.Date;

import nl.topicus.onderwijs.entities.Evenement;

public class EvenementZoekFilter extends AbstractZoekFilter<Evenement>
{

	private static final long serialVersionUID = 1L;

	private String naam;

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

}

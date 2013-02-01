package nl.topicus.onderwijs.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import nl.topicus.cobra.entities.RestrictedAccess;

import org.hibernate.annotations.AccessType;

@Entity
@AccessType("field")
public class Account extends Entiteit
{

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@RestrictedAccess(hasSetter = false)
	private String gebruikersnaam;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evenementHost")
	private List<Evenement> evenementen;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	private List<EvenementDeelname> deelnames;

	protected Account()
	{
	}

	public Account(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	public List<Evenement> getEvenementen()
	{
		return evenementen;
	}

	public void setEvenementen(List<Evenement> evenementen)
	{
		this.evenementen = evenementen;
	}

	public List<EvenementDeelname> getDeelnames()
	{
		return deelnames;
	}

	public void setDeelnames(List<EvenementDeelname> deelnames)
	{
		this.deelnames = deelnames;
	}

	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}
}

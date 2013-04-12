package nl.topicus.onderwijs.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import nl.topicus.cobra.entities.RestrictedAccess;

import org.hibernate.annotations.AccessType;

@Entity
@AccessType("field")
public class EvenementDeelname extends Entiteit
{
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private Account account;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deelname")
	private List<EvenementMaaltijd> maaltijden;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evenement", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private Evenement evenement;

	protected EvenementDeelname()
	{

	}

	public EvenementDeelname(Account account, Evenement evenement)
	{
		this.account = account;
		this.evenement = evenement;
	}

	public Account getAccount()
	{
		return account;
	}

	public List<EvenementMaaltijd> getMaaltijden()
	{
		return maaltijden;
	}

	public void setMaaltijden(List<EvenementMaaltijd> maaltijden)
	{
		this.maaltijden = maaltijden;
	}

	public Evenement getEvenement()
	{
		return evenement;
	}
}

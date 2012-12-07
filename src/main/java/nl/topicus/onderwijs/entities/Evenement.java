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
public class Evenement extends Entiteit
{

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evenementHost", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private final Account evenementHost;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evenement")
	private List<EvenementDeelname> deelnames;

	public Evenement(Account evenementHost)
	{
		this.evenementHost = evenementHost;
	}

	public Account getEvenementHost()
	{
		return evenementHost;
	}

	public List<EvenementDeelname> getDeelnames()
	{
		return deelnames;
	}

	public void setDeelnames(List<EvenementDeelname> deelnames)
	{
		this.deelnames = deelnames;
	}
}

package nl.topicus.onderwijs.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	private Account evenementHost;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "evenement")
	private List<EvenementDeelname> deelnames;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date datum;

	public Evenement()
	{

	}

	public Evenement(Account evenementHost, Date datum)
	{
		this.evenementHost = evenementHost;
		this.datum = datum;
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

	public Date getDatum()
	{
		return datum;
	}
}

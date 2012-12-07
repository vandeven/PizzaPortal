package nl.topicus.onderwijs.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import nl.topicus.cobra.entities.RestrictedAccess;

import org.hibernate.annotations.AccessType;

@Entity
@AccessType("field")
public class EvenementMaaltijd extends Entiteit
{

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deelname", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private final EvenementDeelname deelname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maaltijd", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private final Maaltijd maaltijd;

	public EvenementMaaltijd(EvenementDeelname deelname, Maaltijd maaltijd)
	{
		this.deelname = deelname;
		this.maaltijd = maaltijd;
	}

	public EvenementDeelname getDeelname()
	{
		return deelname;
	}

	public Maaltijd getMaaltijd()
	{
		return maaltijd;
	}

}

package nl.topicus.onderwijs.entities;

import javax.persistence.Column;
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
	private EvenementDeelname deelname;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maaltijd", nullable = false)
	@RestrictedAccess(hasSetter = false)
	private Maaltijd maaltijd;

	@Column(nullable = false)
	private int aantal;

	protected EvenementMaaltijd()
	{

	}

	public EvenementMaaltijd(EvenementDeelname deelname, Maaltijd maaltijd, int aantal)
	{
		this.deelname = deelname;
		this.maaltijd = maaltijd;
		this.aantal = aantal;
	}

	public EvenementDeelname getDeelname()
	{
		return deelname;
	}

	public Maaltijd getMaaltijd()
	{
		return maaltijd;
	}

	public int getAantal()
	{
		return aantal;
	}

	public void setAantal(int aantal)
	{
		this.aantal = aantal;
	}

}

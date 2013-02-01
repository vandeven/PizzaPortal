package nl.topicus.onderwijs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import nl.topicus.cobra.entities.RestrictedAccess;

import org.hibernate.annotations.AccessType;

@Entity
@AccessType("field")
public class Maaltijd extends Entiteit
{
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@RestrictedAccess(hasSetter = false)
	private String naam;

	@Column(nullable = false)
	@RestrictedAccess(hasSetter = false)
	private String ingredienten;

	@Column(nullable = false)
	@RestrictedAccess(hasSetter = false)
	private MaaltijdCategorie maaltijdCategorie;

	protected Maaltijd()
	{
	}

	public Maaltijd(MaaltijdCategorie maaltijdCategorie, String naam, String ingredienten)
	{
		this.maaltijdCategorie = maaltijdCategorie;
		this.naam = naam;
		this.ingredienten = ingredienten;
	}

	public String getNaam()
	{
		return naam;
	}

	public String getIngredienten()
	{
		return ingredienten;
	}

	public MaaltijdCategorie getMaaltijdCategorie()
	{
		return maaltijdCategorie;
	}

	public enum MaaltijdCategorie
	{
		Pizza("Pizza", "pizzaImage");

		private final String naam;

		private final String imageClass;

		MaaltijdCategorie(String naam, String imageClass)
		{
			this.naam = naam;
			this.imageClass = imageClass;
		}

		public String getImageClass()
		{
			return imageClass;
		}

		@Override
		public String toString()
		{
			return naam;
		}
	}
}

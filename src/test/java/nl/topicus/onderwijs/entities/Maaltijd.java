package nl.topicus.onderwijs.entities;

import javax.persistence.Entity;

import org.hibernate.annotations.AccessType;

@Entity
@AccessType("field")
public class Maaltijd extends Entiteit
{
	private static final long serialVersionUID = 1L;

	public Maaltijd()
	{

	}
}

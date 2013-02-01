package nl.topicus.onderwijs.panels.menu;

import nl.topicus.onderwijs.pages.AbstractBasePage;
import nl.topicus.onderwijs.pages.evenement.EvenementenPage;
import nl.topicus.onderwijs.pages.persoonlijk.PersoonlijkPage;

public enum MenuItem
{
	Evenementen("Evenementen", EvenementenPage.class),
	Persoonlijk("Persoonlijk", PersoonlijkPage.class);

	private final String naam;

	private final Class< ? extends AbstractBasePage> clazz;

	MenuItem(String naam, Class< ? extends AbstractBasePage> clazz)
	{
		this.naam = naam;
		this.clazz = clazz;
	}

	public Class< ? extends AbstractBasePage> getMenuLink()
	{
		return clazz;
	}

	@Override
	public String toString()
	{
		return naam;
	}
}

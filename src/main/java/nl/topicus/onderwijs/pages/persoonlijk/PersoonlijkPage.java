package nl.topicus.onderwijs.pages.persoonlijk;

import nl.topicus.onderwijs.pages.AbstractSecureBasePage;
import nl.topicus.onderwijs.panels.menu.MenuItem;

public class PersoonlijkPage extends AbstractSecureBasePage
{

	private static final long serialVersionUID = 1L;

	public PersoonlijkPage()
	{

	}

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Persoonlijk;
	}

}

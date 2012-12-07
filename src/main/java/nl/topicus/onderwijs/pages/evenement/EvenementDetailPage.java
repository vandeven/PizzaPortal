package nl.topicus.onderwijs.pages.evenement;

import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractBasePage;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

public class EvenementDetailPage extends AbstractBasePage
{

	private static final long serialVersionUID = 1L;

	public EvenementDetailPage(IModel<Evenement> evenement)
	{
		add(new Link<Evenement>("joinEvent", evenement)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(new EvenementBestelPage(getModel()));
			}

		});
	}
}

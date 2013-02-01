package nl.topicus.onderwijs.pages.evenement;

import nl.topicus.cobra.modelsv2.ModelFactory;
import nl.topicus.onderwijs.EvenementParameterDecoder;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractMenuBasePage;
import nl.topicus.onderwijs.panels.menu.MenuItem;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EvenementDetailPage extends AbstractMenuBasePage
{

	private static final long serialVersionUID = 1L;

	public EvenementDetailPage(PageParameters parameters)
	{
		this(ModelFactory.getModel(EvenementParameterDecoder.decodeParameters(parameters)));
	}

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

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Evenementen;
	}
}

package nl.topicus.onderwijs.pages.evenement;

import nl.topicus.cobra.modelsv2.ModelFactory;
import nl.topicus.onderwijs.EvenementParameterDecoder;
import nl.topicus.onderwijs.PizzaSession;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.models.EclipseLinkModel;
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

	public EvenementDetailPage(final IModel<Evenement> evenement)
	{
		add(new Link<Evenement>("joinEvent", evenement)
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				EvenementDeelname deelname =
					new EvenementDeelname(PizzaSession.get().getAccount().getObject(),
						evenement.getObject());
				deelname.saveAndCommit();

				setResponsePage(new EvenementBestelPage(new EclipseLinkModel<EvenementDeelname>(
					deelname)));
			}

		});
	}

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Evenementen;
	}
}

package nl.topicus.onderwijs.pages.evenement;

import java.util.Date;

import nl.topicus.onderwijs.components.ClickableDataView;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.dao.providers.EvenementenDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractMenuBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.Model;

public class EvenementenPage extends AbstractMenuBasePage
{

	private static final long serialVersionUID = 1L;

	public EvenementenPage()
	{
		EvenementZoekFilter filter = new EvenementZoekFilter();

		ClickableDataView<Evenement> listView =
			new ClickableDataView<Evenement>("evenementenList", new EvenementenDataProvider(filter))
			{

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(Item<Evenement> item)
				{
					setResponsePage(new EvenementDetailPage(item.getModel()));
				}

				@Override
				protected void populateItem(Item<Evenement> item)
				{
					Evenement evenement = item.getModelObject();
					item.add(new Label("naam", evenement.getNaam()));
					item.add(new Label("organisator"));
					item.add(new Label("lokatie", evenement.getLokatie()));
					item.add(new Label("datum", new Model<Date>(evenement.getDatum())));
				}

			};
		add(listView);
	}

}

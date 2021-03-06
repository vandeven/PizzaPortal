package nl.topicus.onderwijs.pages;

import java.util.Date;

import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.dao.providers.EvenementenDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.panels.LoginPanel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

public class HomePage extends AbstractBasePage
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		add(new LoginPanel("loginPanel"));

		addEvents("upcomingEventList", false);
		addEvents("previousEventList", true);
	}

	private void addEvents(String id, boolean voorHuidigeDatum)
	{
		EvenementZoekFilter eventFilter = new EvenementZoekFilter();
		eventFilter.addOrderByProperty("datum");
		eventFilter.addOrderByProperty("naam");
		eventFilter.setAscending(true);

		if (voorHuidigeDatum)
		{
			eventFilter.setVoorDatum(new Date());
		}
		else
		{
			eventFilter.setOpOfNaDatum(new Date());
		}

		DataView<Evenement> eventList =
			new DataView<Evenement>(id, new EvenementenDataProvider(eventFilter), 3)
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<Evenement> item)
				{
					Evenement event = item.getModelObject();
					item.add(new Label("datum", new Model<Date>(event.getDatum())));
					item.add(new Label("naam", event.getNaam()));
				}

			};
		add(eventList);
	}

}

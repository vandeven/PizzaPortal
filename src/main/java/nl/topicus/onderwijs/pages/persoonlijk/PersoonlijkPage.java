package nl.topicus.onderwijs.pages.persoonlijk;

import java.util.Date;

import nl.topicus.onderwijs.PizzaSession;
import nl.topicus.onderwijs.dao.filters.EvenementDeelnameZoekFilter;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.dao.providers.EvenementDeelnameDataProvider;
import nl.topicus.onderwijs.dao.providers.EvenementenDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.pages.AbstractSecureBasePage;
import nl.topicus.onderwijs.panels.menu.MenuItem;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

public class PersoonlijkPage extends AbstractSecureBasePage
{

	private static final long serialVersionUID = 1L;

	public PersoonlijkPage()
	{
		EvenementZoekFilter eventFilter = new EvenementZoekFilter();
		eventFilter.addOrderByProperty("datum");
		eventFilter.addOrderByProperty("naam");
		eventFilter.setAccount(PizzaSession.get().getAccount());
		eventFilter.setAscending(true);

		DataView<Evenement> eventList =
			new DataView<Evenement>("eigenEvent", new EvenementenDataProvider(eventFilter))
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

		EvenementDeelnameZoekFilter filter = new EvenementDeelnameZoekFilter();
		filter.setAccount(PizzaSession.get().getAccount());

		DataView<EvenementDeelname> eventList2 =
			new DataView<EvenementDeelname>("deelneemEvent", new EvenementDeelnameDataProvider(
				filter))
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<EvenementDeelname> item)
				{
					EvenementDeelname event = item.getModelObject();
					item.add(new Label("datum", new Model<Date>(event.getEvenement().getDatum())));
					item.add(new Label("naam", event.getEvenement().getNaam()));
				}

			};
		add(eventList2);
	}

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Persoonlijk;
	}

}

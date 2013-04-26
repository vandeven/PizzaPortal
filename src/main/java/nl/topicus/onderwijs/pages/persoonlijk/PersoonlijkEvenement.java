package nl.topicus.onderwijs.pages.persoonlijk;

import java.util.Iterator;
import java.util.Map;

import nl.topicus.onderwijs.dao.filters.EvenementDeelnameZoekFilter;
import nl.topicus.onderwijs.dao.filters.EvenementMaaltijdZoekFilter;
import nl.topicus.onderwijs.dao.providers.EvenementDeelnameDataProvider;
import nl.topicus.onderwijs.dao.providers.EvenementMaaltijdDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.entities.EvenementMaaltijd;
import nl.topicus.onderwijs.entities.Maaltijd;
import nl.topicus.onderwijs.pages.AbstractMenuBasePage;
import nl.topicus.onderwijs.panels.menu.MenuItem;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class PersoonlijkEvenement extends AbstractMenuBasePage
{

	private static final long serialVersionUID = 1L;

	private Map<String, Integer> totaalPerMaaltijdMap;

	public PersoonlijkEvenement(IModel<Evenement> evenement)
	{

		EvenementDeelnameZoekFilter filter = new EvenementDeelnameZoekFilter();
		filter.setEvenement(evenement);

		totaalPerMaaltijdMap = Maps.newHashMap();

		EvenementDeelnameDataProvider provider = new EvenementDeelnameDataProvider(filter);
		Iterator< ? extends EvenementDeelname> iterator = provider.iterator(0, provider.size());
		while (iterator.hasNext())
		{
			EvenementDeelname deelname = iterator.next();
			for (EvenementMaaltijd evenementMaaltijd : deelname.getMaaltijden())
			{
				Maaltijd maaltijd = evenementMaaltijd.getMaaltijd();
				String maaltijdNaam = maaltijd.getNaam();
				if (totaalPerMaaltijdMap.containsKey(maaltijdNaam))
				{
					totaalPerMaaltijdMap.put(maaltijdNaam, totaalPerMaaltijdMap.get(maaltijdNaam)
						+ evenementMaaltijd.getAantal());
				}
				else
				{
					totaalPerMaaltijdMap.put(maaltijdNaam, evenementMaaltijd.getAantal());
				}
			}

		}

		ListView<String> aantalPizzasList =
			new ListView<String>("aantalPizzasList", Lists.newArrayList(totaalPerMaaltijdMap
				.keySet()))
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<String> item)
				{
					String maaltijdNaam = item.getModelObject();
					item.add(new Label("maaltijd", maaltijdNaam));
					item.add(new Label("aantal", totaalPerMaaltijdMap.get(maaltijdNaam)));
				}
			};
		add(aantalPizzasList);

		DataView<EvenementDeelname> pizzaPerPersoonDataView =
			new DataView<EvenementDeelname>("pizzaPerPersoonDataView",
				new EvenementDeelnameDataProvider(filter))
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<EvenementDeelname> item)
				{
					EvenementDeelname deelname = item.getModelObject();
					item.add(new Label("naam", deelname.getAccount().getGebruikersnaam()));

					EvenementMaaltijdZoekFilter maaltijdFilter = new EvenementMaaltijdZoekFilter();
					maaltijdFilter.setEvenementDeelname(item.getModel());
					DataView<EvenementMaaltijd> pizzaPerPersoonDataView =
						new DataView<EvenementMaaltijd>("maaltijden",
							new EvenementMaaltijdDataProvider(maaltijdFilter))
						{

							private static final long serialVersionUID = 1L;

							@Override
							protected void populateItem(Item<EvenementMaaltijd> maaltijdItem)
							{
								EvenementMaaltijd evenementMaaltijd = maaltijdItem.getModelObject();
								maaltijdItem
									.add(new Label("aantal", evenementMaaltijd.getAantal()));
								maaltijdItem.add(new Label("maaltijd", evenementMaaltijd
									.getMaaltijd().getNaam()));
							}

						};
					item.add(pizzaPerPersoonDataView);

				}

			};
		add(pizzaPerPersoonDataView);
	}

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Persoonlijk;
	}

}

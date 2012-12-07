package nl.topicus.onderwijs.pages.evenement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.topicus.cobra.modelsv2.ModelFactory;
import nl.topicus.cobra.web.components.listview.ClickableIdObjectListView;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;

public class EvenementenPage extends AbstractBasePage
{

	private static final long serialVersionUID = 1L;

	public EvenementenPage()
	{
		List<Evenement> evenementen = new ArrayList<Evenement>();

		Evenement evenement = new Evenement(null, new Date());
		evenementen.add(evenement);

		ClickableIdObjectListView<Evenement> listView =
			new ClickableIdObjectListView<Evenement>("evenementenList",
				ModelFactory.getListModel(evenementen))
			{

				private static final long serialVersionUID = 1L;

				@Override
				public void onClick(ListItem<Evenement> item)
				{
					setResponsePage(new EvenementDetailPage(item.getModelObject()));
				}

				@Override
				protected void populateItem(ListItem<Evenement> item)
				{
					item.add(new Label("naam"));
					item.add(new Label("organisator"));
					item.add(new Label("lokatie"));
					item.add(new Label("datum"));
				}

			};
		add(listView);
	}

}

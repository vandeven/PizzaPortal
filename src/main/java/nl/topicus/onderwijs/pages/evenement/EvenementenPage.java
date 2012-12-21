package nl.topicus.onderwijs.pages.evenement;

import java.util.Date;

import nl.topicus.cobra.web.components.text.DatumField;
import nl.topicus.onderwijs.components.ClickableDataView;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.dao.providers.EvenementenDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractMenuBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.core.options.Options;

public class EvenementenPage extends AbstractMenuBasePage
{

	private static final long serialVersionUID = 1L;

	public EvenementenPage()
	{
		EvenementZoekFilter filter = new EvenementZoekFilter();

		Form<EvenementZoekFilter> filterForm =
			new Form<EvenementZoekFilter>("filterForm",
				new CompoundPropertyModel<EvenementZoekFilter>(filter));
		filterForm.add(new TextField<String>("naam"));
		DatumField datumField = new DatumField("datum")
		{
			private static final long serialVersionUID = 1L;

			@Override
			public Options getOptions()
			{
				Options options = super.getOptions();
				options.removeOption("buttonImage");
				options.removeOption("buttonImageOnly");
				options.removeOption("showButtonPanel");
				options.removeOption("buttonText");
				options.removeOption("showOn");
				return options;
			}
		};

		filterForm.add(datumField);
		add(filterForm);

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
					item.add(new Label("organisator", evenement.getEvenementHost()
						.getGebruikersnaam()));
					item.add(new Label("lokatie", evenement.getLokatie()));
					item.add(new Label("datum", new Model<Date>(evenement.getDatum())));
				}

			};
		add(listView);
	}

}

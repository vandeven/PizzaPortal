package nl.topicus.onderwijs.pages.evenement;

import java.util.Date;

import nl.topicus.cobra.web.components.text.DatumField;
import nl.topicus.onderwijs.PizzaSession;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.AbstractSecureBasePage;
import nl.topicus.onderwijs.providers.EvenementProvider;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.core.options.Options;

public class EvenementRegisterPage extends AbstractSecureBasePage
{

	private static final long serialVersionUID = 1L;

	private IModel<Evenement> model = null;

	public EvenementRegisterPage()
	{
		super();
		model =
			new Model<Evenement>(new Evenement(PizzaSession.get().getAccount().getObject(),
				new Date()));

		Form<Evenement> eForm =
			new Form<Evenement>("eForm", new CompoundPropertyModel<Evenement>(model))
			{

				private static final long serialVersionUID = 1L;

				@Override
				public void onSubmit()
				{
					EvenementProvider provider = new EvenementProvider();
					provider.begin();
					model.getObject().saveOrUpdate();
					provider.commit();
				}
			};

		eForm.add(new TextField<String>("naam"));
		eForm.add(new TextField<String>("lokatie"));

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

		eForm.add(datumField);
		add(eForm);
	}
}

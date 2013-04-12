package nl.topicus.onderwijs.pages.evenement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.topicus.cobra.util.ComponentUtil;
import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.dao.providers.MaaltijdDataProvider;
import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.entities.EvenementMaaltijd;
import nl.topicus.onderwijs.entities.Maaltijd;
import nl.topicus.onderwijs.pages.AbstractSecureBasePage;
import nl.topicus.onderwijs.panels.menu.MenuItem;
import nl.topicus.onderwijs.providers.MaaltijdProvider;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.odlabs.wiquery.ui.draggable.DraggableBehavior;
import org.odlabs.wiquery.ui.draggable.DraggableHelper;
import org.odlabs.wiquery.ui.droppable.DroppableBehavior;
import org.odlabs.wiquery.ui.droppable.DroppableBehavior.AjaxDropCallback;

public class EvenementBestelPage extends AbstractSecureBasePage
{
	private static final long serialVersionUID = 1L;

	private HashMap<Long, Integer> besteldeMaaltijdenMap;

	private List<Long> verwijderdeMaaltijdenMap = new ArrayList<Long>();

	private IModel<EvenementDeelname> evenementDeelnameModel;

	public EvenementBestelPage(IModel<EvenementDeelname> evenementDeelname)
	{
		this.evenementDeelnameModel = evenementDeelname;

		vulBesteldeMaaltijden();

		MaaltijdZoekFilter maaltijdFilter = new MaaltijdZoekFilter();
		DataView<Maaltijd> maaltijdenList =
			new DataView<Maaltijd>("maaltijdenList", new MaaltijdDataProvider(maaltijdFilter))
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(Item<Maaltijd> item)
				{
					Maaltijd maaltijd = item.getModelObject();
					item.add(new WebMarkupContainer("image").add(new AttributeModifier("class",
						maaltijd.getMaaltijdCategorie().getImageClass())));
					item.add(new Label("categorie", maaltijd.getMaaltijdCategorie().toString()));
					item.add(new Label("naam", maaltijd.getNaam()));
					item.add(new DraggableBehavior().setAppendTo("body").setHelper(
						DraggableHelper.CLONE));
				}

			};
		WebMarkupContainer maaltijdenListContainer =
			new WebMarkupContainer("maaltijdenListContainer");
		maaltijdenListContainer.add(maaltijdenList);
		add(maaltijdenListContainer);

		WebMarkupContainer besteldeMaaltijdenListContainer =
			new WebMarkupContainer("besteldeMaaltijdenListContainer");
		besteldeMaaltijdenListContainer.add(new DroppableBehavior()
			.setDropEvent(new MaaltijdDropEvent()));
		besteldeMaaltijdenListContainer.setOutputMarkupPlaceholderTag(true);
		add(besteldeMaaltijdenListContainer);

		ListView<Long> besteldeMaaltijdenList =
			new ListView<Long>("besteldeMaaltijdenList", new BesteldeMaaltijdModel())
			{

				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Long> item)
				{
					Maaltijd maaltijd = new MaaltijdProvider().get(item.getModelObject());
					item.add(new WebMarkupContainer("image").add(new AttributeModifier("class",
						maaltijd.getMaaltijdCategorie().getImageClass())));
					item.add(new Label("categorie", maaltijd.getMaaltijdCategorie().toString()));
					item.add(new Label("naam", maaltijd.getNaam()));
					item.add(new Label("aantal", new Model<Integer>(getBesteldeMaaltijdenMap().get(
						item.getModelObject()))));
					item.add(new AjaxLink<Long>("del", item.getModel())
					{

						private static final long serialVersionUID = 1L;

						@SuppressWarnings("unchecked")
						@Override
						public void onClick(AjaxRequestTarget target)
						{
							Long key = getModelObject();
							Integer aantal = getBesteldeMaaltijdenMap().get(key) - 1;
							if (aantal < 1)
							{
								getBesteldeMaaltijdenMap().remove(key);
								getVerwijderdeMaaltijden().add(key);
							}
							else
							{
								getBesteldeMaaltijdenMap().put(key, aantal);
							}
							((ListView<Long>) getPage().get(
								"besteldeMaaltijdenListContainer:besteldeMaaltijdenList"))
								.setModel(new BesteldeMaaltijdModel());
							target.add(getPage().get("besteldeMaaltijdenListContainer:"));
						}

					});
				}

			};
		besteldeMaaltijdenListContainer.add(besteldeMaaltijdenList);
		besteldeMaaltijdenListContainer.add(new Link<Void>("bestellingAfronden")
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				MaaltijdProvider provider = new MaaltijdProvider();

				EvenementDeelname deelname = evenementDeelnameModel.getObject();

				List<EvenementMaaltijd> evenementMaaltijdList = new ArrayList<EvenementMaaltijd>();

				for (Long id : getVerwijderdeMaaltijden())
				{
					// TODO
					//
					// for (EvenementMaaltijd m :
					// evenementDeelnameModel.getObject().getMaaltijden())
					// {
					// if (m.getMaaltijd().getId() == id)
					// {
					// EvenementMaaltijdProvider emProvider = new
					// EvenementMaaltijdProvider();
					// EvenementMaaltijd z =
					// emProvider.getReference(EvenementMaaltijd.class, id);
					// emProvider.remove(z);
					// }
					// }
				}

				for (Long id : getBesteldeMaaltijdenMap().keySet())
				{
					Maaltijd maaltijd = provider.get(id);

					// Kijk of maaltijd al een deelname heeft
					EvenementMaaltijd evenementMaaltijd = null;
					for (EvenementMaaltijd eMaaltijd : evenementDeelnameModel.getObject()
						.getMaaltijden())
					{
						if (eMaaltijd.getMaaltijd().getId() == id)
							evenementMaaltijd = eMaaltijd;
					}

					if (evenementMaaltijd == null)
					{
						evenementMaaltijd =
							new EvenementMaaltijd(deelname, maaltijd, getBesteldeMaaltijdenMap()
								.get(id));
						evenementMaaltijd.save();
					}
					else
					{
						evenementMaaltijd.setAantal(getBesteldeMaaltijdenMap().get(id));
						evenementMaaltijd.updateAndCommit();
					}
					evenementMaaltijdList.add(evenementMaaltijd);
				}
				deelname.setMaaltijden(evenementMaaltijdList);
				deelname.updateAndCommit();

				setResponsePage(EvenementenPage.class);
			}

		});
	}

	private List<Long> getVerwijderdeMaaltijden()
	{
		return verwijderdeMaaltijdenMap;
	}

	private void vulBesteldeMaaltijden()
	{
		besteldeMaaltijdenMap = new HashMap<Long, Integer>();
		for (EvenementMaaltijd eMaaltijd : evenementDeelnameModel.getObject().getMaaltijden())
		{
			besteldeMaaltijdenMap.put(eMaaltijd.getMaaltijd().getId(), eMaaltijd.getAantal());
		}
	}

	private HashMap<Long, Integer> getBesteldeMaaltijdenMap()
	{
		return besteldeMaaltijdenMap;
	}

	@Override
	public MenuItem getMenuItem()
	{
		return MenuItem.Evenementen;
	}

	private class BesteldeMaaltijdModel extends LoadableDetachableModel<List<Long>>
	{

		private static final long serialVersionUID = 1L;

		@Override
		protected List<Long> load()
		{
			return new ArrayList<Long>(getBesteldeMaaltijdenMap().keySet());
		}

	}

	private class MaaltijdDropEvent extends AjaxDropCallback
	{

		private static final long serialVersionUID = 1L;

		@Override
		protected void drop(AjaxRequestTarget target, Component source, Component dropped)
		{
			@SuppressWarnings("unchecked")
			Item<Maaltijd> item = (Item<Maaltijd>) dropped;
			Maaltijd maaltijd = item.getModelObject();

			if (getBesteldeMaaltijdenMap().containsKey(maaltijd.getId()))
			{
				Integer value = getBesteldeMaaltijdenMap().get(maaltijd.getId()) + 1;
				getBesteldeMaaltijdenMap().put(maaltijd.getId(), value);
			}
			else
			{
				getBesteldeMaaltijdenMap().put(maaltijd.getId(), new Integer(1));
			}
			target.add(getPage().get("besteldeMaaltijdenListContainer"));
		}

	}

	@Override
	protected void onDetach()
	{
		ComponentUtil.detachQuietly(evenementDeelnameModel);
		super.onDetach();
	}
}

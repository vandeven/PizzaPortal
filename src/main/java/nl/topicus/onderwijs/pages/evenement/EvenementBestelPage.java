package nl.topicus.onderwijs.pages.evenement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.dao.providers.MaaltijdDataProvider;
import nl.topicus.onderwijs.entities.Evenement;
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

	public EvenementBestelPage(IModel<Evenement> evenement)
	{
		besteldeMaaltijdenMap = new HashMap<Long, Integer>();
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

						@Override
						public void onClick(AjaxRequestTarget target)
						{
							Long key = getModelObject();
							Integer aantal = getBesteldeMaaltijdenMap().get(key);
							if (aantal == 1)
							{
								getBesteldeMaaltijdenMap().remove(key);
							}
							else
							{
								aantal--;
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
			Item<Maaltijd> item = (Item<Maaltijd>) dropped;
			Maaltijd maaltijd = item.getModelObject();

			if (getBesteldeMaaltijdenMap().containsKey(maaltijd.getId()))
			{
				Integer value = getBesteldeMaaltijdenMap().get(maaltijd.getId());
				value = value + 1;
				getBesteldeMaaltijdenMap().put(maaltijd.getId(), value);
			}
			else
			{
				getBesteldeMaaltijdenMap().put(maaltijd.getId(), new Integer(1));
			}
			target.add(getPage().get("besteldeMaaltijdenListContainer"));
		}

	}
}

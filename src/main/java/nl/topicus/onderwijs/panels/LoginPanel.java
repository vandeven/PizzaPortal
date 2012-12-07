package nl.topicus.onderwijs.panels;

import javax.inject.Inject;

import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.pages.evenement.EvenementenPage;
import nl.topicus.onderwijs.providers.AccountProvider;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class LoginPanel extends Panel
{

	@Inject
	private AccountProvider provider;

	private static final long serialVersionUID = 1L;

	public LoginPanel(String id)
	{
		super(id);
		add(new Link<Void>("login")
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(EvenementenPage.class);
			}

		});
		ListView<Account> listview = new ListView<Account>("listview", provider.getAccounts())
		{

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Account> item)
			{
				final Account account = item.getModelObject();
				item.add(new Label("id", account.getId().toString()));
				item.add(new Label("name", account.getGebruikersnaam()));
			}

		};
		add(listview);
	}
}

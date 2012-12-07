package nl.topicus.onderwijs.panels;

import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.providers.AccountProvider;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

public class LoginPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public LoginPanel(String id)
	{
		super(id);
		AccountProvider acc = new AccountProvider();

		ListView<Account> listview = new ListView<Account>("listview", acc.getAccounts())
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

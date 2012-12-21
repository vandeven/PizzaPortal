package nl.topicus.onderwijs.panels;

import javax.inject.Inject;

import nl.topicus.onderwijs.dao.filters.AccountZoekFilter;
import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.pages.evenement.EvenementenPage;
import nl.topicus.onderwijs.providers.AccountProvider;
import nl.topicus.onderwijs.security.AuthenticationUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

public class LoginPanel extends Panel
{

	@Inject
	private AccountProvider provider;

	private static final long serialVersionUID = 1L;

	public LoginPanel(String id)
	{
		super(id);
		Form<Void> form = new Form<Void>("form")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit()
			{
				String username = get("username").getDefaultModelObjectAsString();
				String password = get("password").getDefaultModelObjectAsString();

				Subject currentUser = SecurityUtils.getSubject();
				boolean authenticated = true;
				try
				{
					currentUser.login(new UsernamePasswordToken(username, password));
				}
				catch (AuthenticationException e)
				{
					authenticated = false;
				}
				if (authenticated)
				{
					try
					{
						AuthenticationUtil.loggedOn(provider, username);
						setResponsePage(EvenementenPage.class);
					}
					catch (Exception e)
					{
						error("Fout tijdens het inloggen");
						e.printStackTrace();
					}
				}
				else
				{
					error("Geen correcte username/password combinatie");
				}
			}
		};
		add(form);
		form.add(new TextField<String>("username", new Model<String>()).setRequired(true));
		form.add(new PasswordTextField("password", new Model<String>()));
		form.add(new SubmitLink("login", form));

		ListView<Account> listview =
			new ListView<Account>("listview", provider.list(new AccountZoekFilter()))
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

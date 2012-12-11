package nl.topicus.onderwijs.panels.menu;

import nl.topicus.onderwijs.pages.HomePage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class MenuPanel extends Panel
{

	private static final long serialVersionUID = 1L;

	public MenuPanel(String id)
	{
		super(id);
		add(new Link<Void>("logout")
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				Subject subject = SecurityUtils.getSubject();
				subject.logout();
				setResponsePage(HomePage.class);
			}

		});
	}

}

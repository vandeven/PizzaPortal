package nl.topicus.onderwijs.pages;

import nl.topicus.onderwijs.resources.bootstrap.BootstrapHeaderItem;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class AbstractBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public AbstractBasePage()
	{
		add(new Link<Void>("home")
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setResponsePage(HomePage.class);
			}

		});
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(BootstrapHeaderItem.get());
		super.renderHead(response);
	}

}

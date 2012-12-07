package nl.topicus.onderwijs;

import nl.topicus.onderwijs.resources.BootstrapHeaderItem;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

public class AbstractBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public AbstractBasePage()
	{
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.render(BootstrapHeaderItem.get());
		super.renderHead(response);
	}

}

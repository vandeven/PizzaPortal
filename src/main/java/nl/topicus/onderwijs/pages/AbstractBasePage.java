package nl.topicus.onderwijs.pages;

import nl.topicus.onderwijs.resources.bootstrap.BootstrapHeaderItem;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class AbstractBasePage extends WebPage
{
	private static final long serialVersionUID = 1L;

	public AbstractBasePage()
	{
		this(null);
	}

	public AbstractBasePage(@SuppressWarnings("unused") PageParameters parameters)
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
		add(new FeedbackPanel("feedback")
		{
			private static final long serialVersionUID = 1L;

			@Override
			protected String getCSSClass(FeedbackMessage message)
			{
				if (message.isError())
				{
					return "alert alert-error";
				}
				if (message.isInfo())
				{
					return "alert alert-info";
				}
				return super.getCSSClass(message);
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

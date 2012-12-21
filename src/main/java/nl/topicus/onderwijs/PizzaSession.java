package nl.topicus.onderwijs;

import nl.topicus.onderwijs.entities.Account;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class PizzaSession extends WebSession
{
	private static final long serialVersionUID = 1L;

	private IModel<Account> account = null;

	public static PizzaSession get()
	{
		return (PizzaSession) WebSession.get();
	}

	public PizzaSession(Request request)
	{
		super(request);
	}

	public IModel<Account> getAccount()
	{
		return account;
	}

	public void setAccount(IModel<Account> account)
	{
		this.account = account;
	}

}

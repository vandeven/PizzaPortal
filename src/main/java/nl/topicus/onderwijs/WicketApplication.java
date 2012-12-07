package nl.topicus.onderwijs;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import nl.topicus.onderwijs.entities.Account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application. If you want to run this application
 * without deploying, run the Start class.
 * 
 * @see nl.topicus.onderwijs.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private static final String PERSISTENCE_UNIT_NAME = "todos";

	private EntityManagerFactory factory;

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class< ? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

		// add your configuration here
		setPersistenceFactory(Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME));

		Account newAccount = new Account();
		newAccount.setGebruikersnaam("pietje");
		newAccount.saveOrUpdateAndCommit();
	}

	public EntityManagerFactory getPersistenceFactory()
	{
		return factory;
	}

	public void setPersistenceFactory(EntityManagerFactory factory)
	{
		this.factory = factory;
	}
}

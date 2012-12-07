package nl.topicus.onderwijs;

import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.ftlines.wicket.cdi.CdiConfiguration;
import nl.topicus.onderwijs.entities.Account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.jboss.weld.environment.servlet.Listener;

/**
 * Application object for your web application. If you want to run this application
 * without deploying, run the Start class.
 * 
 * @see nl.topicus.onderwijs.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	private static final String PERSISTENCE_UNIT_NAME = "todos";

	private static EntityManagerFactory factory = Persistence
		.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

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

		BeanManager manager =
			(BeanManager) getServletContext().getAttribute(Listener.BEAN_MANAGER_ATTRIBUTE_NAME);

		new CdiConfiguration(manager).configure(this);

		// Test data:
		Account newAccount = new Account();
		newAccount.setGebruikersnaam("pietje");
		newAccount.saveOrUpdateAndCommit();
	}

	public EntityManagerFactory getPersistenceFactory()
	{
		return factory;
	}
}

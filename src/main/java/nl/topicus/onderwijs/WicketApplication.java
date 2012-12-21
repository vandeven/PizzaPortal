package nl.topicus.onderwijs;

import java.util.Date;

import javax.enterprise.inject.spi.BeanManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.ftlines.wicket.cdi.CdiConfiguration;
import nl.topicus.onderwijs.entities.Account;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.pages.HomePage;
import nl.topicus.onderwijs.pages.evenement.EvenementDetailPage;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.jboss.weld.environment.servlet.Listener;
import org.joda.time.DateTime;

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
		addTestData();
		setSecuritySettings();
		setBookmarkablePages();
	}

	private void setSecuritySettings()
	{
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
	}

	private void addTestData()
	{
		Account newAccount = new Account();
		newAccount.setGebruikersnaam("pietje");
		newAccount.saveOrUpdateAndCommit();

		Evenement evenement = new Evenement(newAccount, new Date());
		evenement.setNaam("test");
		evenement.setLokatie("testlocatie");
		evenement.saveOrUpdateAndCommit();

		Evenement evenement2 = new Evenement(newAccount, DateTime.now().minusDays(1).toDate());
		evenement2.setNaam("test2");
		evenement2.setLokatie("testlocatie2");
		evenement2.saveOrUpdateAndCommit();

		Evenement evenement3 = new Evenement(newAccount, DateTime.now().minusDays(2).toDate());
		evenement3.setNaam("test3");
		evenement3.setLokatie("testlocatie3");
		evenement3.saveOrUpdateAndCommit();

		Evenement evenement4 = new Evenement(newAccount, DateTime.now().minusDays(3).toDate());
		evenement4.setNaam("test4");
		evenement4.setLokatie("testlocatie4");
		evenement4.saveOrUpdateAndCommit();

		Evenement evenement5 = new Evenement(newAccount, DateTime.now().minusDays(5).toDate());
		evenement5.setNaam("test5");
		evenement5.setLokatie("testlocatie5");
		evenement5.saveOrUpdateAndCommit();

		Evenement evenement6 = new Evenement(newAccount, DateTime.now().minusDays(6).toDate());
		evenement6.setNaam("test6");
		evenement6.setLokatie("testlocatie6");
		evenement6.saveOrUpdateAndCommit();
	}

	private void setBookmarkablePages()
	{
		mountPage(EvenementParameterDecoder.getPath(), EvenementDetailPage.class);
	}

	public EntityManagerFactory getPersistenceFactory()
	{
		return factory;
	}

	@Override
	public PizzaSession newSession(Request request, Response response)
	{
		return new PizzaSession(request);
	}
}

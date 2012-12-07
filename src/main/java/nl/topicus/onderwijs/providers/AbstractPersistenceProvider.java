package nl.topicus.onderwijs.providers;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.topicus.onderwijs.WicketApplication;

import org.apache.wicket.protocol.http.WebApplication;

public abstract class AbstractPersistenceProvider<T>
{
	private EntityManager em = null;

	public void begin()
	{
		WicketApplication app = ((WicketApplication) WebApplication.get());
		em = app.getPersistenceFactory().createEntityManager();
		em.getTransaction().begin();
	}

	public Query createQuery(String query)
	{
		return em.createQuery(query);
	}

	public void commit()
	{
		em.getTransaction().commit();
	}

	public void persist(T entity)
	{
		em.persist(entity);
	}

	public void remove(T entity)
	{
		em.remove(entity);
	}

	public void end()
	{
		em.close();
	}
}

package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import nl.topicus.cobra.entities.IdObject;
import nl.topicus.onderwijs.WicketApplication;
import nl.topicus.onderwijs.dao.filters.AbstractZoekFilter;

import org.apache.wicket.protocol.http.WebApplication;

import com.google.common.collect.Lists;

@ApplicationScoped
public abstract class AbstractPersistenceProvider<T extends IdObject, ZF extends AbstractZoekFilter<T>>
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

	public List<T> list(ZF filter)
	{
		return list(filter, null, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> list(ZF filter, Integer first, Integer count)
	{
		List<T> list = null;
		String queryString = createQuery(filter);
		if (queryString == null || queryString.isEmpty())
		{
			return Lists.newArrayList();
		}
		begin();
		Query query = createQuery(queryString);
		if (first != null && count != null)
		{
			query.setFirstResult(first);
			query.setMaxResults(count);
		}
		list = query.getResultList();
		end();
		return list;
	}

	public int count(ZF filter)
	{
		int result = 0;
		String queryString = createCountQuery(filter);
		if (queryString == null || queryString.isEmpty())
		{
			return 0;
		}
		begin();
		Query query = createQuery(queryString);
		result = ((Number) query.getSingleResult()).intValue();
		end();
		return result;
	}

	public T get(Class<T> clazz, Long id)
	{
		T entiteit = null;
		String queryString = "select e from :entiteitNaam where e.id = :id";
		begin();
		Query query = createQuery(queryString);
		query.setParameter("entiteit", clazz.getSimpleName());
		query.setParameter("id", id);
		entiteit = (T) query.getSingleResult();
		end();
		return entiteit;
	}

	protected abstract String createQuery(ZF filter);

	protected abstract String createCountQuery(ZF filter);
}

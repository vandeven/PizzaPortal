package nl.topicus.onderwijs.providers;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

@ApplicationScoped
public class EvenementProvider extends AbstractPersistenceProvider<Evenement, EvenementZoekFilter>
{

	@Override
	protected Predicate createWhere(Root<Evenement> root, CriteriaBuilder builder,
			EvenementZoekFilter filter)
	{
		return null;
	}

	@Override
	protected Class<Evenement> getEntityClass()
	{
		return Evenement.class;
	}

}

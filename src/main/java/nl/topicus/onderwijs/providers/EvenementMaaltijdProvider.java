package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.EvenementMaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.EvenementMaaltijd;

@ApplicationScoped
public class EvenementMaaltijdProvider extends
		AbstractPersistenceProvider<EvenementMaaltijd, EvenementMaaltijdZoekFilter>
{

	@Override
	protected Class<EvenementMaaltijd> getEntityClass()
	{
		return EvenementMaaltijd.class;
	}

	@Override
	protected List<Predicate> createWhere(Root<EvenementMaaltijd> root, CriteriaBuilder cb,
			EvenementMaaltijdZoekFilter filter)
	{
		PredicateBuilder builder = new PredicateBuilder(root, cb);

		if (filter.getEvenementDeelname() != null)
		{
			builder.addEq("deelname", filter.getEvenementDeelname().getObject());
		}

		return builder.build();
	}
}

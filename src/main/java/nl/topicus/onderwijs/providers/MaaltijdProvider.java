package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.Maaltijd;

public class MaaltijdProvider extends AbstractPersistenceProvider<Maaltijd, MaaltijdZoekFilter>
{

	@Override
	protected List<Predicate> createWhere(Root<Maaltijd> root, CriteriaBuilder cb,
			MaaltijdZoekFilter filter)
	{
		PredicateBuilder builder = new PredicateBuilder(root, cb);

		if (filter.getMaaltijdIds() != null)
		{
			builder.addIn("id", filter.getMaaltijdIds());
		}
		return builder.build();
	}

	@Override
	protected Class<Maaltijd> getEntityClass()
	{
		return Maaltijd.class;
	}

}

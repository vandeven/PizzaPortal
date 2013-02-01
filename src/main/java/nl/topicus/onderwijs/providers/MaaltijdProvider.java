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
	protected List<Predicate> createWhere(Root<Maaltijd> root, CriteriaBuilder builder,
			MaaltijdZoekFilter filter)
	{
		return null;
	}

	@Override
	protected Class<Maaltijd> getEntityClass()
	{
		return Maaltijd.class;
	}

}

package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

import com.google.common.collect.Lists;

@ApplicationScoped
public class EvenementProvider extends AbstractPersistenceProvider<Evenement, EvenementZoekFilter>
{

	@Override
	protected List<Predicate> createWhere(Root<Evenement> root, CriteriaBuilder builder,
			EvenementZoekFilter filter)
	{
		List<Predicate> predicates = Lists.newArrayList();

		if (filter.getNaam() != null)
		{
			predicates.add(builder.like(root.<String> get("naam"), "%" + filter.getNaam() + "%"));
		}
		return predicates;
	}

	@Override
	protected Class<Evenement> getEntityClass()
	{
		return Evenement.class;
	}

}

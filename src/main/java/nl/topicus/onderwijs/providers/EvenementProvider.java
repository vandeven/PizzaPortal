package nl.topicus.onderwijs.providers;

import java.util.List;

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
	protected List<Predicate> createWhere(Root<Evenement> root, CriteriaBuilder cb,
			EvenementZoekFilter filter)
	{
		PredicateBuilder builder = new PredicateBuilder(root, cb);

		if (filter.getNaam() != null)

			builder.addLike("naam", filter.getNaam());

		if (filter.getDatum() != null)
			builder.addEq("datum", filter.getDatum());

		if (filter.getVoorDatum() != null)
			builder.addLower("datum", filter.getVoorDatum());

		if (filter.getOpOfNaDatum() != null)
			builder.addGe("datum", filter.getOpOfNaDatum());
		if (filter.getAccount() != null && filter.getAccount().getObject() != null)
			builder.addEq("evenementHost", filter.getAccount().getObject());

		return builder.build();
	}

	@Override
	protected Class<Evenement> getEntityClass()
	{
		return Evenement.class;
	}

}

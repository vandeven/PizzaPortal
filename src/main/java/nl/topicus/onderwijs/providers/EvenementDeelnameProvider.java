package nl.topicus.onderwijs.providers;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.topicus.onderwijs.dao.filters.EvenementDeelnameZoekFilter;
import nl.topicus.onderwijs.entities.EvenementDeelname;

public class EvenementDeelnameProvider extends
		AbstractPersistenceProvider<EvenementDeelname, EvenementDeelnameZoekFilter>
{

	@Override
	protected List<Predicate> createWhere(Root<EvenementDeelname> root, CriteriaBuilder cb,
			EvenementDeelnameZoekFilter filter)
	{
		PredicateBuilder builder = new PredicateBuilder(root, cb);

		if (filter.getAccount() != null)
			builder.addEq("account", filter.getAccount().getObject());
		if (filter.getEvenement() != null)
			builder.addEq("evenement", filter.getEvenement().getObject());

		return builder.build();
	}

	@Override
	protected Class<EvenementDeelname> getEntityClass()
	{
		return EvenementDeelname.class;
	}

}

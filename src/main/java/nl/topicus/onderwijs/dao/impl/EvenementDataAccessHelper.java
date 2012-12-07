package nl.topicus.onderwijs.dao.impl;

import nl.topicus.cobra.dao.hibernate.AbstractZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

import org.hibernate.Criteria;

public class EvenementDataAccessHelper extends
		AbstractZoekFilterDataAccessHelper<Evenement, EvenementZoekFilter> implements
		nl.topicus.onderwijs.dao.helpers.EvenementDataAccessHelper
{

	@Override
	protected Criteria createCriteria(EvenementZoekFilter filter)
	{
		return null;
	}

}
package nl.topicus.onderwijs.dao.impl;

import nl.topicus.cobra.dao.hibernate.AbstractZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.Maaltijd;

import org.hibernate.Criteria;

public class MaaltijdDataAccessHelper extends
		AbstractZoekFilterDataAccessHelper<Maaltijd, MaaltijdZoekFilter> implements
		nl.topicus.onderwijs.dao.helpers.MaaltijdDataAccessHelper
{

	@Override
	protected Criteria createCriteria(MaaltijdZoekFilter filter)
	{
		return null;
	}

}
package nl.topicus.onderwijs.dao.helpers;

import nl.topicus.cobra.dao.helpers.BatchZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.MaaltijdZoekFilter;
import nl.topicus.onderwijs.entities.Maaltijd;

public interface MaaltijdDataAccessHelper extends
		BatchZoekFilterDataAccessHelper<Maaltijd, MaaltijdZoekFilter>
{

}

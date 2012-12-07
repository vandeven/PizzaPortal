package nl.topicus.onderwijs.dao.helpers;

import nl.topicus.cobra.dao.helpers.BatchZoekFilterDataAccessHelper;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;

public interface EvenementDataAccessHelper extends
		BatchZoekFilterDataAccessHelper<Evenement, EvenementZoekFilter>
{

}

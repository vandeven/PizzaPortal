package nl.topicus.onderwijs.dao.filters;

import nl.topicus.onderwijs.entities.EvenementDeelname;
import nl.topicus.onderwijs.entities.EvenementMaaltijd;

import org.apache.wicket.model.IModel;

public class EvenementMaaltijdZoekFilter extends AbstractZoekFilter<EvenementMaaltijd>
{

	private static final long serialVersionUID = 1L;

	private IModel<EvenementDeelname> evenementDeelname;

	public IModel<EvenementDeelname> getEvenementDeelname()
	{
		return evenementDeelname;
	}

	public void setEvenementDeelname(IModel<EvenementDeelname> evenementDeelname)
	{
		this.evenementDeelname = evenementDeelname;
	}

}

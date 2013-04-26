package nl.topicus.onderwijs.dao.filters;

import java.util.List;

import nl.topicus.onderwijs.entities.Maaltijd;

public class MaaltijdZoekFilter extends AbstractZoekFilter<Maaltijd>
{

	private static final long serialVersionUID = 1L;

	private List<Long> maaltijdIds;

	public List<Long> getMaaltijdIds()
	{
		return maaltijdIds;
	}

	public void setMaaltijdIds(List<Long> maaltijdIds)
	{
		this.maaltijdIds = maaltijdIds;
	}

}

package nl.topicus.onderwijs.dao.providers;

import java.io.Serializable;
import java.util.Iterator;

import nl.topicus.cobra.util.ComponentUtil;
import nl.topicus.onderwijs.dao.filters.EvenementZoekFilter;
import nl.topicus.onderwijs.entities.Evenement;
import nl.topicus.onderwijs.models.ELModelFactory;
import nl.topicus.onderwijs.providers.EvenementProvider;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

public class EvenementenDataProvider implements IDataProvider<Evenement>, Serializable
{

	private static final long serialVersionUID = 1L;

	private final EvenementZoekFilter filter;

	public EvenementenDataProvider(EvenementZoekFilter filter)
	{
		this.filter = filter;
	}

	@Override
	public Iterator< ? extends Evenement> iterator(long first, long count)
	{
		EvenementProvider provider = new EvenementProvider();
		return provider.list(filter, new Long(first).intValue(), new Long(count).intValue())
			.iterator();
	}

	@Override
	public long size()
	{
		EvenementProvider provider = new EvenementProvider();
		return provider.count(filter);
	}

	@Override
	public IModel<Evenement> model(Evenement object)
	{
		return ELModelFactory.getModel(object);
	}

	@Override
	public void detach()
	{
		ComponentUtil.detachQuietly(filter);
	}

}

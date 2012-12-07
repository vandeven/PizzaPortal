package nl.topicus.onderwijs.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;

import nl.topicus.cobra.dao.BatchDataAccessHelper;
import nl.topicus.cobra.dao.DataAccessRegistry;
import nl.topicus.cobra.entities.IdObject;
import nl.topicus.cobra.entities.TransientIdObject;

import org.hibernate.annotations.AccessType;

@MappedSuperclass
@AccessType("field")
public class Entiteit implements Serializable, TransientIdObject
{
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue()
	@AccessType("property")
	private Long id;

	@Transient
	private Serializable temporaryId;

	@Version
	private Long version = 0L;

	public Entiteit()
	{

	}

	@Override
	public Serializable getIdAsSerializable()
	{
		return getId();
	}

	@Override
	public boolean isSaved()
	{
		return (getId() != null && getId().longValue() > 0);
	}

	@Override
	public Long getVersion()
	{
		return version;
	}

	@Override
	public void setVersion(Long version)
	{
		this.version = version;
	}

	@Override
	public Serializable getTemporaryId()
	{
		return temporaryId;
	}

	@Override
	public void setTemporaryId(Serializable tempId)
	{
		this.temporaryId = tempId;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Override
	public int hashCode()
	{
		if (getTemporaryId() != null)
			return getTemporaryId().hashCode();
		if (getId() != null)
			return getId().hashCode();
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj instanceof Entiteit)
		{
			Entiteit other = (Entiteit) obj;
			if (getTemporaryId() != null)
				return getTemporaryId().equals(other.getTemporaryId());

			if (getId() != null)
				return getId().equals(other.getId());
			// Else fallthrough naar false...
		}
		return false;
	}

	protected <T extends IdObject> BatchDataAccessHelper<T> getBatchDataAccessHelper()
	{
		return DataAccessRegistry.getHelper(BatchDataAccessHelper.class);
	}

	public Serializable save()
	{
		return getBatchDataAccessHelper().save(this);
	}

	public void update()
	{
		getBatchDataAccessHelper().update(this);
	}

	public void saveOrUpdate()
	{
		getBatchDataAccessHelper().saveOrUpdate(this);
	}

	public void delete()
	{
		getBatchDataAccessHelper().delete(this);
	}

	public void commit()
	{
		getBatchDataAccessHelper().batchExecute();
	}

	public final Serializable saveAndCommit()
	{
		Serializable ret = save();
		commit();
		return ret;
	}

	public final void updateAndCommit()
	{
		update();
		commit();
	}

	public final void saveOrUpdateAndCommit()
	{
		saveOrUpdate();
		commit();
	}

	public final void deleteAndCommit()
	{
		delete();
		commit();
	}
}

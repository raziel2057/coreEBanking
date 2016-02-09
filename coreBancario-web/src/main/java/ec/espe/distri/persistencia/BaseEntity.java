package ec.espe.distri.persistencia;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Version;

public abstract class BaseEntity {
	@Id
	protected ObjectId id;
	protected Date lastChange;
	
	@Version
	private long version;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getLastChange() {
		return lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", lastChange=" + lastChange + "]";
	}
	
	
	
}

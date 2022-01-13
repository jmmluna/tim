package es.jmmluna.tim.domain.model.work;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.domain.model.CommonRepository;

public interface WorkRepository extends CommonRepository<Work, WorkId>{

	public long getActiveCount();

	public List<Work> getActives();

	public List<Work> getInactives();	
	
	public UUID getNextIdentifier();
	
}

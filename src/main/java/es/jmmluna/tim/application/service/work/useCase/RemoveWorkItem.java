package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveWorkItem {

	@Autowired
	private WorkRepository repository;
	@Autowired
	private WorkMapper mapper;

	public WorkDTO execute(UUID workId, WorkItemDTO workItemDTO) {
		var work = repository.findById(WorkId.of(workId));
		work.remove(mapper.toWorkItem(workItemDTO));
		
		var savedWork = repository.save(work);
		return mapper.toDTO(savedWork);
	}
}

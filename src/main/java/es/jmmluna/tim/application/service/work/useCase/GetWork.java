package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetWork {

	@Autowired
	private WorkRepository repository;
	@Autowired
	private WorkMapper mapper;

	public WorkDTO execute(UUID uuid) {
		var work = this.repository.findById(WorkId.of(uuid));
		return mapper.toDTO(work);
	}
}

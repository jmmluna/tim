package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateWork {

	@Autowired
	private WorkRepository repository;
	@Autowired
	private WorkMapper mapper;

	public WorkDTO execute(WorkDTO workDTO) {
		if (workDTO.getUuid() == null)
			throw new IdentifierNotAllowedException("Null UUID is not allowed");
				
		var work = repository.save(mapper.toModel(workDTO));
		return mapper.toDTO(work);
	}
}

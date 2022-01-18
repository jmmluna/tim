package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CreateWork {

	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private WorkMapper mapper;

	public WorkDTO execute(WorkDTO workDTO) {
		if (workDTO.getUuid() != null)
			throw new IdentifierNotAllowedException("Not null UUID are not allowed");

		workDTO.setUuid(workRepository.getNextIdentifier());
		workDTO.setDate(new Date());
		var work = workRepository.save(mapper.toModel(workDTO));
		return mapper.toDTO(work);
	}
}

package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetActiveWorkCount {

	private final WorkRepository repository;

	@Autowired
	public GetActiveWorkCount(WorkRepository repository) {
		this.repository = repository;
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}

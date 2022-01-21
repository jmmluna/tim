package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.domain.model.work.WorkRepository;
import es.jmmluna.tim.domain.model.work.WorkStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetInitiatedWorkCount {

	@Autowired
	private  WorkRepository repository;

	
	public Long execute() {
		return this.repository.getStatusCount(WorkStatus.INITIATED);	
	}	
}

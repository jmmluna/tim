package es.jmmluna.tim.application.service.employee.hour.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourId;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;

@Service
public class DeleteEmployeeHour {
    @Autowired
    private EmployeeHourRepository repository;

    public void execute(UUID uuid) {
    	var employeeHour = this.repository.findById(EmployeeHourId.of(uuid));	
        this.repository.delete(employeeHour);
    }
}

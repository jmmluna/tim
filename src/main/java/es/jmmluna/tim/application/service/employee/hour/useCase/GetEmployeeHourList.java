package es.jmmluna.tim.application.service.employee.hour.useCase;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourMapper;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHour;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;
import es.jmmluna.tim.domain.model.work.WorkStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetEmployeeHourList {
    @Autowired
    private EmployeeHourRepository repository;
    @Autowired
    private EmployeeHourMapper mapper;

    public List<EmployeeHourDTO> execute(WorkStatus workStatus) {    	
    	var employeeHours = workStatus == WorkStatus.ALL? this.repository.findAll():this.repository.findAll(workStatus); 
        return this.toEmployeeHourDTOList(employeeHours);
    }

    private List<EmployeeHourDTO> toEmployeeHourDTOList(Iterable<EmployeeHour> employeeHours) {
        var dtos = new ArrayList<EmployeeHourDTO>();
        employeeHours.forEach(employeeHour -> dtos.add(mapper.toDTO(employeeHour)));
        return dtos;
    }
}

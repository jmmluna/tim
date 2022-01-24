package es.jmmluna.tim.application.service.employee.hour.useCase;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourMapper;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveEmployeeHour {
    @Autowired
    private EmployeeHourRepository repository;

    @Autowired
    private EmployeeHourMapper mapper;

    public EmployeeHourDTO execute(EmployeeHourDTO employeeHourDTO) {
        var employeeHour = this.repository.save(mapper.toModel(employeeHourDTO));
        return mapper.toDTO(employeeHour);
    }
}

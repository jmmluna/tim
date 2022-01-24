package es.jmmluna.tim.infrastructure.persistence.employee.hour;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHour;
import es.jmmluna.tim.domain.model.employee.hour.EmployeeHourId;
import es.jmmluna.tim.domain.model.work.WorkId;

@Component
public class EmployeeHourEntityMapper {

    public EmployeeHour toModel(EmployeeHourEntity entity) {
        return new EmployeeHour(EmployeeHourId.of(entity.getUuid()), EmployeeId.of(entity.getEmployeeId()), WorkId.of(entity.getWorkId()),
                entity.getDate(), entity.getHours(), Price.of(entity.getPrice()));
    }

    public EmployeeHourEntity toEntity(EmployeeHour employeeHour) {
        var entity = new EmployeeHourEntity();
        entity.setUuid(employeeHour.getEmployeeHourId().getValue());
        entity.setEmployeeId(employeeHour.getEmployeeId().getValue());
        entity.setWorkId(employeeHour.getWorkId().getValue());
        entity.setHours(employeeHour.getHours());
        entity.setPrice(employeeHour.getPrice().getValue());
        entity.setDate(employeeHour.getDate());

        return entity;
    }
}

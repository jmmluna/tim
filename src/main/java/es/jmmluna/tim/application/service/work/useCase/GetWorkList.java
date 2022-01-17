package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.work.Work;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetWorkList {
    private final WorkRepository repository;
    @Autowired
    private WorkMapper mapper;

    @Autowired
    public GetWorkList(WorkRepository repository) {
        this.repository = repository;
    }

    public List<WorkDTO> execute(EElementList type) {
        List<WorkDTO> works;

        if (type == EElementList.ALL)
            works = this.toWorkDTOList(this.repository.findAll());
        else if (type == EElementList.ACTIVE)
            works = this.toWorkDTOList(this.repository.getActives());
        else
            works = this.toWorkDTOList(this.repository.getInactives());

        return works;
    }

    private List<WorkDTO> toWorkDTOList(Iterable<Work> works) {
        var dtos = new ArrayList<WorkDTO>();

        works.forEach(work -> dtos.add(mapper.toDTO(work)));
        return dtos;
    }

}

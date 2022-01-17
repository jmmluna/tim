package es.jmmluna.tim.application.service.work.useCase;

import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DisableWork {

    @Autowired
    private final WorkRepository repository;

    @Autowired
    public DisableWork(WorkRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID uuid) {
        var work = this.repository.findById(WorkId.of(uuid));
        this.repository.delete(work);
    }
}

package es.jmmluna.tim.domain.model.work;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.domain.model.CommonRepository;

public interface WorkRepository extends CommonRepository<Work, WorkId> {

    long getActiveCount();

    List<Work> getActives();

    List<Work> getInactives();

    UUID getNextIdentifier();

}

package es.jmmluna.tim.infrastructure.persistence.work;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.domain.model.work.Work;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;

@Repository
public class WorkDBRepository implements WorkRepository {

    @Autowired
    private WorkEntityMapper mapper;

    @Autowired
    private WorkJpaRepository workRepository;

    @Override
    public long getActiveCount() {
        return workRepository.countByExpirationDate(null);
    }

    @Override
    public Work save(Work work) {
        var savedEntity = workRepository.save(mapper.toEntity(work));
        return mapper.toModel(savedEntity);
    }

    @Override
    public Work findById(WorkId workId) {
        Optional<WorkEntity> result = workRepository.findById(workId.getValue());
        var entity = result.get();
        return mapper.toModel(entity);
    }

    @Override
    public void delete(Work work) {
        work.deactivate();
        this.save(work);
    }

    @Override
    public List<Work> findAll() {
        var entities = workRepository.findAll();
        return toList(entities);
    }

    @Override
    public List<Work> getActives() {
        var entities = workRepository.findByExpirationDate(null);
        return toList(entities);
    }

    @Override
    public List<Work> getInactives() {
        var entities = workRepository.findByExpirationDateIsNotNull();
        return toList(entities);
    }

    private List<Work> toList(List<WorkEntity> workEntities) {
        var works = new ArrayList<Work>();
        workEntities.forEach(entity -> works.add(mapper.toModel(entity)));
        return works;
    }

    @Override
    public UUID getNextIdentifier() {
        return UUID.randomUUID();
    }
}

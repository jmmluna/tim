package es.jmmluna.tim.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.repository.EmployeeRepository;



@Service
public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	@Autowired
	private EmployeeRepository employeeRepository;
	
//		
//	@PersistenceContext
//	  private EntityManager entityManager;
//	

	@Override
	public List<Employee> getAll() {
		return employeeRepository.findAll();		
	}
	
	
//	public void showPersistenceData() {
//		LOGGER.info("*************TABLA-ENTITY***************");
//		
//		MetamodelImplementor metaModelImpl = (MetamodelImplementor)entityManager.getMetamodel();
//		Map<String, EntityPersister> entityPersisters = metaModelImpl.entityPersisters();
//		Collection<EntityPersister> val = entityPersisters.values();               
//
//		for (EntityPersister ep : val) {
//		        AbstractEntityPersister aep = (AbstractEntityPersister)ep;
//		        LOGGER.info("Nombre de tabla: " +  aep.getTableName());
//		        LOGGER.info("Nombre de entidad: " +  aep.getEntityName());
//
//		 }
//	}
}
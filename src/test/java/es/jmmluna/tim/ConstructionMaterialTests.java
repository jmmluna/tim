package es.jmmluna.tim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialByIdService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialCountService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialDTO;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialDeletionByIdService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialDeletionService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialListingService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialSaveService;
import es.jmmluna.tim.infrastructure.TimApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = TimApplication.class)
@Slf4j
@DisplayName("Customer test")
//@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ConstructionMaterialTests {

	@Autowired
	private ConstructionMaterialByIdService constructionMaterialByIdService;

	@Autowired
	private ConstructionMaterialSaveService constructionMaterialSaveService;

	@Autowired
	private ConstructionMaterialListingService constructionMaterialListingService;

	@Autowired
	private ConstructionMaterialCountService constructionMaterialCountService;

	@Autowired
	private ConstructionMaterialDeletionService constructionMaterialDeletionService;
	
	@Autowired
	private ConstructionMaterialDeletionByIdService constructionMaterialDeletionByIdService;

	@Test
	@Sql("classpath:construction-materials-test-data.sql")
	@DisplayName("Initialize construction materials")
	@Order(1)
	public void shouldSaveConstructionMaterialsThroughSqlFile() {
		var constructionMaterialDTO = constructionMaterialByIdService.execute("123e4567-e89b-12d3-a456-556642440000");
		assertNotNull(constructionMaterialDTO, "El material de construcción debe estar registrado");
		assertEquals(constructionMaterialDTO.getDescription(), "SACA DE ARENA", "No coincide el nombre del material de construcción");
	}

	@Test
	@DisplayName("Construction material creation")
	@Order(2)
	public void testConstructionMaterialCreation() {
		var constructionMaterialDTO = this.getExternalConstructionMaterial();
		var savedConstructionMaterialDTO = this.constructionMaterialSaveService.execute(constructionMaterialDTO);
		assertEquals(savedConstructionMaterialDTO.getUuid(), constructionMaterialDTO.getUuid(), "No coincide el id del material de construcción");
		assertEquals(savedConstructionMaterialDTO.getDescription(), constructionMaterialDTO.getDescription(), "No coincide la descripción del material de descripción");
	}

	@Test
	@DisplayName("Active construction material listing")
	@Order(3)
	public void testActiveConstructionMaterialListing() {
		List<ConstructionMaterialDTO> constructionMaterials = this.constructionMaterialListingService.execute(EElementList.ACTIVE);
		assertTrue(constructionMaterials.size() == 3);
	}

	@Test
	@DisplayName("Active construction material count")
	@Order(4)
	public void testActiveConstructionMaterialCount() {
		Long constructionMaterialCount = this.constructionMaterialCountService.execute();
		assertTrue(constructionMaterialCount == 3);
	}

	@Test
	@DisplayName("Delete construction material")
	@Order(5)
	public void testDeleteConstructionMaterial() {

		var constructionMaterialDTO = this.constructionMaterialByIdService.execute("123e4567-e89b-12d3-a456-556642440000");
		var deletedConstructionMaterialDTO = constructionMaterialDeletionService.execute(constructionMaterialDTO);

		assertEquals(constructionMaterialDTO.getDescription(), deletedConstructionMaterialDTO.getDescription(),
				"No coincide la descripción del material de construcción eliminado");

		assertNull(constructionMaterialDTO.getExpirationDate(), "La fecha de baja tiene que ser nula");
		assertNotNull(deletedConstructionMaterialDTO.getExpirationDate(), "El material de construcción tiene que tener asignado la fecha de baja");

		Long constructionMaterialCount = this.constructionMaterialCountService.execute();
		assertTrue(constructionMaterialCount == 2, "El número de materiales de construcción no es correcto después de eliminar");
	}

	@Test
	@DisplayName("Delete construction material by Id")
	@Order(6)
	public void testDeleteConstructionMaterialById() {
		var deletedConstructionMaterialDTO = constructionMaterialDeletionByIdService.execute("123e4567-e89b-12d3-a456-556642440001");

		assertEquals("123e4567-e89b-12d3-a456-556642440001", deletedConstructionMaterialDTO.getUuid().toString(), "No coincide el nombre del material de construcción eliminado");
		assertNotNull(deletedConstructionMaterialDTO.getExpirationDate(), "El material de construcción tiene que tener asignado la fecha de baja");

		Long customerCount = this.constructionMaterialCountService.execute();
		assertTrue(customerCount == 1, "El número de materiales de construcción no es correcto después de eliminar");

	}

	private ConstructionMaterialDTO getExternalConstructionMaterial() {
		ConstructionMaterialDTO constructionMaterialDTO = new ConstructionMaterialDTO();
		constructionMaterialDTO.setUuid(UUID.randomUUID());
		constructionMaterialDTO.setDescription("SACO DE CEMENTO");
		constructionMaterialDTO.setPrice(150.50);		

		return constructionMaterialDTO;
	}
}

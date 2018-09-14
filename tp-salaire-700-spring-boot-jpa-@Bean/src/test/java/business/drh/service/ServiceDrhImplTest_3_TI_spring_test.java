package business.drh.service;

import business.config.AppConfig;
import business.drh.dao.EmployeDao;
import business.drh.dao.LoadDao;
import business.drh.model.Employe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Integration test of ServiceDrhImpl with spring-test
 *
 * @author Gauthier Peel
 */
//@ContextConfiguration(classes = AppConfig.class)
@SpringBootTest(classes = AppConfig.class)
public class ServiceDrhImplTest_3_TI_spring_test extends AbstractTestNGSpringContextTests {

    Logger logger = LoggerFactory.getLogger(ServiceDrhImpl.class);

    @Autowired
    ServiceDrh serviceDrh;

    @Autowired
    EmployeDao emplloyeDao;

    @Autowired
    LoadDao loadDao;

    @BeforeClass
    public void beforeClass() {
        loadDao.load();
    }

    @Test
    public void testHappyPath() {
        Long ID_EMPLOYE = 10L;
        Employe EMPLOYE = new Employe("toto", "dupond");
        EMPLOYE.setId(ID_EMPLOYE);

        serviceDrh.payerSalaire(ID_EMPLOYE, 7000);

        logger.debug("fini !");
    }
}

package business.drh.service;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import business.config.AppConfig;
import business.drh.dao.LoadDao;

public class Main {

	public static void main(String[] args) throws Exception {

		ConfigurableApplicationContext context = SpringApplication.run(AppConfig.class);

		LoadDao loadDao = context.getBean(LoadDao.class);
		loadDao.load();

		ServiceDrh serviceDrh = context.getBean(ServiceDrh.class);
		serviceDrh.payerSalaire(10L, 2000);

		System.out.println("fini");
		//
		context.close();
	}
}

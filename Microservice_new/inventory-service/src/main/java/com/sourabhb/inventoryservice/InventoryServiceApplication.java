package com.sourabhb.inventoryservice;

import com.sourabhb.inventoryservice.model.Inventory;
import com.sourabhb.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);

		InventoryServiceApplication app = new InventoryServiceApplication();

		// Call the loadData method on the instance
		app.loadData(null);
//		loadData(null).run();
	}


	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository)
	{
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("D");
			inventory.setQuantity(800);



			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("f");
			inventory1.setQuantity(0);


			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}

package com.marceloserpa.aggregatefun.orders;

import com.marceloserpa.aggregatefun.DataBaseConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootTest(classes = DataBaseConfig.class)
@AutoConfigureDataJdbc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PurchaseOrderTest {


    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(
            "postgres:11.1")
            .withDatabaseName("mypoc")
            .withUsername("marceloserpa")
            .withPassword("123456")
            .withInitScript("init.sql")
            ;


    @Autowired
    PurchaseOrderRepository repository;

    @Test
    public void createUpdateDeleteOrder() {

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setShippingAddress("Oiii");
        purchaseOrder.addItem(4, "Captain Future Comet Lego set");
        purchaseOrder.addItem(2, "Cute blue angler fish plush toy");

        PurchaseOrder saved = repository.save(purchaseOrder);

        Assertions.assertEquals(repository.count(), 1);
        Assertions.assertEquals(repository.countItems(), 2);

        repository.delete(saved);

        Assertions.assertEquals(repository.count(), 0);
        Assertions.assertEquals(repository.countItems(), 0);

    }

    @BeforeAll
    public static void start(){
        container.start();
    }

    @AfterAll
    public static void close(){
        container.stop();
        container.close();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }


}
package com.marceloserpa.aggregatefun;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(classes = DataBaseConfig.class)
@AutoConfigureDataJdbc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PurchaseOrderTest {

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
    }


}
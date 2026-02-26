package marceloserpa;

public class ProductViewEvent {

    private String productId;

    public ProductViewEvent() {}

    public ProductViewEvent(String productId) {
        this.productId = productId;
    }

    public String getProductId(){
        return this.productId;
    }

}

package Model;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static ProductList instance;
    private List<Product> productList;

    private ProductList() {
        productList = new ArrayList<>();
        productList.add(new Product(1, "P001", "Apple iPhone 13", 799.99, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-midnight-select-20220606?wid=904&hei=840&fmt=jpeg&qlt=80&.v=1654122893576"));
        productList.add(new Product(2, "P002", "Samsung Galaxy S21", 699.99, "https://m.media-amazon.com/images/I/71o8Q5XJS5L._AC_SL1500_.jpg"));
        productList.add(new Product(3, "P003", "Google Pixel 6", 599.99, "https://m.media-amazon.com/images/I/61u48FEs2TL._AC_SL1500_.jpg"));
        productList.add(new Product(4, "P004", "OnePlus 9", 729.99, "https://example.com/images/oneplus9.jpg"));
        productList.add(new Product(5, "P005", "Sony WH-1000XM4 Headphones", 349.99, "https://example.com/images/sonyheadphones.jpg"));
        productList.add(new Product(6, "P006", "Dell XPS 13 Laptop", 999.99, "https://example.com/images/dellxps13.jpg"));
        productList.add(new Product(7, "P007", "Apple MacBook Air", 1099.99, "https://example.com/images/macbookair.jpg"));
        productList.add(new Product(8, "P008", "Samsung QLED TV", 1199.99, "https://example.com/images/samsungqled.jpg"));
        productList.add(new Product(9, "P009", "Amazon Echo Dot", 49.99, "https://example.com/images/echodot.jpg"));
        productList.add(new Product(10, "P010", "Nintendo Switch", 299.99, "https://example.com/images/switch.jpg"));
    }

    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }
}
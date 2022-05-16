package vn.ntp.webCafe.sevice;

import vn.ntp.webCafe.model.Product;
import vn.ntp.webCafe.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public final static String PATH = "data/products.csv";

    private static ProductService instance;

    private ProductService() {

    }

    public static ProductService getInstance() {
        if (instance == null)
            instance = new ProductService();
            return instance;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            products.add(Product.parse(record));
        }
        return products;
    }

    @Override
    public void add(Product newProduct) {
        List<Product> products = findAll();
        newProduct.setCreatedAt(Instant.now());
        products.add(newProduct);
        CSVUtils.write(PATH, products);

    }

    @Override
    public void update(Product newProduct) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public boolean exist(int id) {
        return false;
    }

    @Override
    public boolean existByName(String name) {
        return false;
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Product> findAllOrderByPriceASC() {
        return null;
    }

    @Override
    public List<Product> findAllOrderByPriceDESC() {
        return null;
    }
}

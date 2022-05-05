package gr.upatras.rest.example;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author ctranoris
 *
 */

@Service
public class ProductService implements IProductService {

    // creating an object of ArrayList
    List<Product> products = new ArrayList<>();

    int ix = 1000;
    /**
     * adding products to the List
     */
    public ProductService() {
        super();

        products.add(new Product(100, "Mobile", "CLK98123", 9000.00, 6));
        products.add(new Product(101, "Smart TV", "LGST09167", 60000.00, 3));
        products.add(new Product(102, "Washing Machine", "38753BK9", 9000.00, 7));
        products.add(new Product(103, "Laptop", "LHP29OCP", 24000.00, 1));
        products.add(new Product(104, "Air Conditioner", "ACLG66721", 30000.00, 5));
        products.add(new Product(105, "Refrigerator ", "12WP9087", 10000.00, 4));
    }

    /**


     * returns a list of product
     */
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Product addProduct(Product p) {
        ix = ix +1; //increase product index
        p.setId( ix );
        products.add( p );
        return p;
    }

    @Override
    public Product editProduct(Product p) {
        Product editProd = findById( p.getId() );
        if ( editProd != null ) {
            editProd.setPname( p.getPname() );
            editProd.setPrice( p.getPrice() );

            return editProd;
        }
        return null;
    }

    @Override
    public Void deleteProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                products.remove(p);
                break;
            }
        }
        return null;

    }
}
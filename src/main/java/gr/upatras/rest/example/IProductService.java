package gr.upatras.rest.example;

import java.util.List;

/**
 * @author ctranoris
 *


 */
public interface IProductService {
    /**
     * @return all products
     */
    List<Product> findAll();

    /**
     * @param id
     * @return a {@link Product}
     */
    Product findById(int id);

    /**
     * @param p
     * @return the @Product added
     */
    Product addProduct(Product p);

    /**
     * @param p
     * @return the edited {@link Product}
     */
    Product editProduct(Product p);


    /**
     * @param id of product
     */
    Void deleteProduct(int id);

}
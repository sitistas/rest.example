package gr.upatras.rest.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ctranoris
 *
 */
public class Category {

    /**
     *
     */
    private int id;

    /**
     * name of category
     */


    private String name;


    /**
     * list of products in category
     */
    private List<Product> products = new ArrayList<>();


    /**
     *
     * constructire
     * @param id
     * @param name
     */
    public Category(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



}
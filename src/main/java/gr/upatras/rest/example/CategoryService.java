package gr.upatras.rest.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ctranoris
 *
 */

@Service
public class CategoryService implements ICategoryService {

    // creating an object of ArrayList
    List<Category> categories = new ArrayList<Category>();

    @Autowired
    private IProductService productService;

    int ix = 10;


    public CategoryService() {
        super();

// categories.add( new Category(1, "TV")); 
// categories.add( new Category(2, "Electronics")); 
// categories.add( new Category(3, "Home & Kitchen")); 
    }

    @Override
    public List<Category> findAll() {
        return categories;
    }



    @Override
    public Category findById(int id) {
        for (Category c : categories) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Category addCategory(Category catToAdd) {
        ix = ix +1; //increase product index
        Category c = new Category(ix, catToAdd.getName());
        for (Product p : catToAdd.getProducts()) {
            Product productToAdd = productService.findById(p.getId());
            if ( productToAdd != null ) {
                c.getProducts().add(productToAdd);
            }

        }
        categories.add( c );
        return c;
    }

    @Override
    public Category editCategory(Category catToAdd) {
        Category editCat = findById( catToAdd.getId() );
        editCat.getProducts().clear();
        if ( editCat != null ) {
            editCat.setName( catToAdd.getName() );
            for (Product p : catToAdd.getProducts()) {
                Product productToAdd = productService.findById(p.getId());
                if ( productToAdd != null ) {
                    editCat.getProducts().add(productToAdd);
                }

            }

            return editCat;
        }
        return null;
    }

    @Override
    public Void deleteCategory(int id) {
        for (Category p : categories) {
            if (p.getId() == id) {
                categories.remove(p);
                break;
            }
        }
        return null;

    }

} 
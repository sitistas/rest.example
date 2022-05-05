package gr.upatras.rest.example;

import java.util.List;

/**
 * @author ctranoris
 *
 */
public interface ICategoryService {
    /**
     * @return all categories
     */


    List<Category> findAll();

    /**
     * @param id
     * @return a {@link Category}
     */
    Category findById(int id);

    /**
     * @param c
     * @return the Category added
     */
    Category addCategory(Category c);

    /**
     * @param c
     * @return the edited {@link Category}
     */
    Category editCategory(Category c);

    /**
     * @param id of Category
     */
    Void deleteCategory(int id);

}
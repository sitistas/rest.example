package gr.upatras.rest.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CategoryController {
        @Autowired
        private ICategoryService categoryService;

        private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

        @ApiOperation(value = "Retrieves all Categorys", notes = "This operation retrieves all Category entities. ", response = Category.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/category/", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.GET)
        public List<Category> getCategory() {
                // finds all the categorys
                // returns the category list
                return categoryService.findAll();
        }

        @ApiOperation(value = "Retrieves a Category by ID", notes = "This operation retrieves a Category entity. ", response = Category.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/category/{id}", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.GET)
        public Category getCategoryById(
                        @ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") int id) {

                log.info(String.format("Will return category with id %s", id));
                return categoryService.findById(id);
        }

        @ApiOperation(value = "Deletes a Category by ID", notes = "This operation retrieves a Category entity. ", response = Category.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Category.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/category/{id}", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.DELETE)
        public ResponseEntity<Void> deletetById(
                        @ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") int id) {

                try {

                        log.info(String.format("Will delete object with id %s", id));
                        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
                } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        @ApiOperation(value = "Creates a Category", notes = "This operation creates a Category entity.", response = Category.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Category.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/category", produces = { "application/json;charset=utf-8" }, consumes = {
                        "application/json;charset=utf-8" }, method = RequestMethod.POST)
        public ResponseEntity<Category> createCategory(
                        @ApiParam(value = "The Category to be created", required = true) @RequestBody Category p) {

                log.info("Will add a new category");
                Category category = categoryService.addCategory(p);
                return new ResponseEntity<>(category, HttpStatus.OK);
        }

        @ApiOperation(value = "Updates partially a Category", nickname = "patchServiceTestSpecification", notes = "This operation updates partially a Category entity.", response = Category.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Updated", response = Category.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/category/{id}", produces = {
                        "application/json;charset=utf-8" }, consumes = {
                                        "application/json;charset=utf-8" }, method = RequestMethod.PATCH)
        ResponseEntity<Category> patchCategory(
                        @ApiParam(value = "The Category to be updated", required = true) @RequestBody Category body,
                        @ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") String id) {

                Category category = categoryService.editCategory(body);
                return new ResponseEntity<>(category, HttpStatus.OK);
        }

}
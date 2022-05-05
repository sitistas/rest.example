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
public class ProductController {
        @Autowired
        private IProductService productService;

        private static final Logger log = LoggerFactory.getLogger(ProductController.class);

        @ApiOperation(value = "Retrieves all products", notes = "This operation retrieves all Product entities. ", response = Product.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })

        @RequestMapping(value = "/product/", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.GET)
        public List<Product> getProduct() {
                // finds all the products
                List<Product> products = productService.findAll();
                // returns the product list
                return products;
        }

        @ApiOperation(value = "Retrieves a Product by ID", notes = "This operation retrieves a Product entity. ", response = Product.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/product/{id}", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.GET)
        public Product getProductById(
                        @ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") int id) {

                log.info(String.format("Will return product with id %s", id));
                Product product = productService.findById(id);
                return product;
        }

        @ApiOperation(value = "Deletes a Product by ID", notes = "This operation retrieves a Product entity. ", response = Product.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Product.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/product/{id}", produces = {
                        "application/json;charset=utf-8" }, method = RequestMethod.DELETE)
        public ResponseEntity<Void> deletetById(
                        @ApiParam(value = "Identifier of the Category", required = true) @PathVariable("id") int id) {

                try {

                        log.info(String.format("Will delete object with id %s", id));
                        return new ResponseEntity<Void>(productService.deleteProduct(id), HttpStatus.OK);
                } catch (Exception e) {
                        log.error("Couldn't serialize response for content type application/json", e);
                        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        @ApiOperation(value = "Creates a Product", notes = "This operation creates a Product entity.", response = Product.class)
        @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = Product.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/product", produces = { "application/json;charset=utf-8" }, consumes = {
                        "application/json;charset=utf-8" }, method = RequestMethod.POST)
        public ResponseEntity<Product> createProduct(
                        @ApiParam(value = "The Product to be created", required = true) @RequestBody Product p) {

                log.info("Will add a new product");
                Product product = productService.addProduct(p);
                return new ResponseEntity<Product>(product, HttpStatus.OK);
        }

        @ApiOperation(value = "Updates partially a Product", nickname = "patchProduct", notes = "This operation updates partially a Product entity.", response = Product.class)
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Updated", response = Product.class),
                        @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                        @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                        @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                        @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                        @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                        @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                        @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })
        @RequestMapping(value = "/product/{id}", produces = {
                        "application/json;charset=utf-8" }, consumes = {
                                        "application/json;charset=utf-8" }, method = RequestMethod.PATCH)
        ResponseEntity<Product> patchProduct(
                        @ApiParam(value = "The Product to be updated", required = true) @RequestBody Product body,
                        @ApiParam(value = "Identifier of the Product", required = true) @PathVariable("id") String id) {

                Product product = productService.editProduct(body);
                return new ResponseEntity<Product>(product, HttpStatus.OK);
        }

}
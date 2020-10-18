package in.jk.springboot.controller;
import in.jk.springboot.request.ProductRequest;
import in.jk.springboot.response.Response;
import in.jk.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@CrossOrigin
//@RestController
//@RequestMapping("/productapi")
public class ProductController {
	/*
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/findProductByProductId/{productId}" , produces = "application/json",  method = RequestMethod.GET)
	public Response findProductIdByProductId(@PathVariable Integer productId) {
		
		return productService.findProductByProductId(productId);
		
	}
	
	@RequestMapping(value = "/product" , produces = "application/json",  method = RequestMethod.POST)
	public Response addProduct(@RequestBody ProductRequest productRequest) {
		
	   return productService.addProduct(productRequest);
		
	}
	
*/

}

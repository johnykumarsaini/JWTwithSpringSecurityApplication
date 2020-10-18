package in.jk.springboot.serivce.impl;

import org.springframework.stereotype.Service;

import in.jk.springboot.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	//@Autowired
	///private ProductRepository productRepository;
	
	/*
	@Override
	public Response findProductByProductId(Integer productId) {
		Response response = null;
		Object responseData=null;

		response = new Response();
		try {
			Product product = productRepository.getOne(productId);
			System.out.println(product);
			responseData =product;
			response.setResponse("200", "Request Successfull.", responseData, "");
			
			
		} catch (Exception e) {

			response.setResponse("500", "Request UnSuccessfull.", productId, e.toString());
		}

		return response;
	}

	@Override
	public Response addProduct(ProductRequest productRequest) {
		Response response = null;
		
		
        response = new Response();
        
        
		try {
			
			Product product = new Product();
			product.setProductId(productRequest.getProductId());
			product.setProductName(productRequest.getProductName());
			product.setPrice(productRequest.getPrice());
			
			 product = productRepository.save(product);
			System.out.println(product);
			
			
			response.setResponse("200", "Request Successfull.", product, "");
		} catch (Exception e) {

			response.setResponse("500", "Request UnSuccessfull.", productRequest, e.getMessage());
		}

		return response;
	}
	
	*/
	}



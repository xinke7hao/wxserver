package com.hunantv.mbp.rest.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

@Path("/payment")
@Service
public class PaymentRestService {

	@GET
	@Path("/{param}")
	public Response sayHello(@PathParam("param") String param){
		String output = "Hello, " + param;
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllPayments() {
		
		String testJson = "{\"errno\":0,\"msg\":\"Success\",\"total_count\":5,\"data\":{"
				+ "\"0\":{\"amount\":\"10000\",\"business_order_id\":\"1\",\"business_result\":\"Success\",\"create_time\":\"2014-11-10 09:30:34\",\"id\":\"1\",\"product_id\":\"1\",\"product_name\":\"商品A\",\"recon_status\":\"N\",\"status\":\"Y\"},"
				+ "\"1\":{\"amount\":\"10000\",\"business_order_id\":\"2\",\"business_result\":\"Success\",\"create_time\":\"2014-11-10 13:30:00\",\"id\":\"2\",\"product_id\":\"1\",\"product_name\":\"商品A\",\"recon_status\":\"N\",\"status\":\"Y\"},"
				+ "\"2\":{\"amount\":\"5000\",\"business_order_id\":\"4\",\"business_result\":\"Fail\",\"create_time\":\"2014-11-10 19:40:34\",\"id\":\"3\",\"product_id\":\"2\",\"product_name\":\"商品B\",\"recon_status\":\"N\",\"status\":\"N\"},"
				+ "\"3\":{\"amount\":\"15000\",\"business_order_id\":\"6\",\"business_result\":\"Success\",\"create_time\":\"2014-11-11 09:45:34\",\"id\":\"4\",\"product_id\":\"3\",\"product_name\":\"商品C\",\"recon_status\":\"N\",\"status\":\"Y\"},"
				+ "\"4\":{\"amount\":\"20000\",\"business_order_id\":\"8\",\"business_result\":\"Success\",\"create_time\":\"2014-11-11 12:30:34\",\"id\":\"5\",\"product_id\":\"4\",\"product_name\":\"商品D\",\"recon_status\":\"N\",\"status\":\"Y\"}"
				+ "}}";
		
		return testJson;
	}
}

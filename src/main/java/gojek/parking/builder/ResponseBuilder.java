package gojek.parking.builder;

/**
 * Builder interface to generate final Response.
 * For the assignment , Builder for Sysout Implementation has been provided.
 *  It can be easily extended to provide implementations for other type of Responses
 *  For ex- In case of Web services , Another Implementation can be JsonResponse Builder
 * @author agarg
 *
 */
public class ResponseBuilder {
	
	public static void buildResponse(String msg){
		System.out.println(msg);
	}

}

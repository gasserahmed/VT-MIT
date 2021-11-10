package api;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * A filter injecting standard response headers.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiResponseFilter implements ContainerResponseFilter {

	public ApiResponseFilter() {
	}

	@Override
	public void filter(ContainerRequestContext requestContext,
					   ContainerResponseContext responseContext) throws IOException {
		MultivaluedMap<String, Object> headers = responseContext.getHeaders();
		headers.putSingle("Cache-Control", "must-revalidate, no-cache, no-store, no-transform, private, proxy-revalidate, max-age=5");
		headers.putSingle("X-Frame-Options", "DENY");
		headers.putSingle("X-XSS-Protection", "1; mode=block");
		headers.putSingle("X-Content-Type", "nosniff");
	}
}

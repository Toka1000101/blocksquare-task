package org.acme.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.acme.exception.ErrorResponse;
import org.acme.exception.custom.SolutionNotFoundException;
import jakarta.ws.rs.ext.ExceptionMapper;

@Provider
public class SolutionNotFoundExceptionMapper  implements ExceptionMapper<SolutionNotFoundException> {
    @Override
    public Response toResponse(SolutionNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(e.getMessage()))
                .build();
    }
}

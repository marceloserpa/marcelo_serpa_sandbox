package com.marceloserpa.redisfun;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Response;
import java.util.List;

import io.smallrye.mutiny.Uni;

@Path("/increments")
public class IncrementResource {

    @Inject
    IncrementService service;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    @POST
    public Uni<Increment> create(Increment increment) {
        return service.set(increment.key, increment.value)
                .onItem().transform(value -> increment); // refactor it
    }

    @GET
    @Path("/{key}")
    public Uni<Response> get(@PathParam("key") String key) {
        return service.get(key)
                .onItem().transform(value -> {
                    if(value == null) {
                        return Response.status(Response.Status.NOT_FOUND);
                    }
                    return Response.ok(new Increment(key, Integer.valueOf(value)));
                })
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("/{key}")
    public Uni<Void> increment(@PathParam("key") String key, Integer value) {
        return service.increment(key, value);
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(@PathParam("key") String key) {
        return service.del(key);
    }
}

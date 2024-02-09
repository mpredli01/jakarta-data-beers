package org.redlich.beers;

import jakarta.data.Sort;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("brewer")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrewerResources {

    @Inject
    BrewerService brewerService;

    @GET
    @Path("/{id}")
    public BrewerResponse findById(@PathParam("id") int id) {
        return brewerService.findById(id)
                .map(BrewerResponse::of)
                .orElseThrow(() -> new NotFoundException());
        }

    @GET
    public List<BrewerResponse> listBrewers() {
        return brewerService.listBrewers()
                .map(BrewerResponse::of)
                .toList();
        }

    @GET
    @Path("/brewer/{name}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name) {
        return brewerService.listBrewersByNameLike(name)
                .map(BrewerResponse::of)
                .toList();
        }

    @GET
    @Path("/brewer/{name}/page/{pageNum}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name,
                                                 @PathParam("pageNum") long pageNum) {
        Pageable pageRequest = Pageable.ofSize(5)
                .page(pageNum)
                .sortBy(Sort.asc("name"), Sort.asc("id"));
        return brewerService.listBrewersByNameLike(name, pageRequest)
                .stream().map(BrewerResponse::of).toList();
        }

    @POST
    @Path("/{id}")
    public BrewerResponse add(@PathParam("id") int id, BrewerRequest request) {
        var brewer = request.createBrewer(id);
        return BrewerResponse.of(brewerService.add(brewer));
        }

    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") int id) {
        brewerService.remove(id);
        }

    @DELETE
    public void removeAll() {
        brewerService.removeAll();
        }
    }

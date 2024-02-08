package org.redlich.beers;

import jakarta.data.Sort;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("beer")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BeerResources {

    @Inject
    BeerService beerService;

    @POST
    @Path("/{id}")
    public BeerResponse add(int id, BeerRequest request) {
        return BeerResponse.of(beerService.add(request.createBeer(id)));
    }

    @DELETE
    @Path("/{id}")
    public void remove(int id){
        beerService.remove(id);
    }

    @DELETE
    public void removeAll(){
        beerService.removeAll();
    }

    @GET
    public List<BeerResponse> listBeers(){
        return beerService.listBeers().map(BeerResponse::of).toList();
    }

    @GET
    @Path("/brewer/{brewer}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName){
        return beerService.listBeersByBrewer(brewerName)
                .map(BeerResponse::of).toList();
    }

    @GET
    @Path("/brewer/{brewer}/page/{pageNum}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName,
                                                 @PathParam("pageNum") long pageNum) {
        Pageable pageRequest = Pageable.ofSize(5)
                .page(pageNum)
                .sortBy(Sort.asc("name"), Sort.asc("id"));
        return beerService.listBeersByBrewer(brewerName, pageRequest).stream()
                .map(BeerResponse::of).toList();

    }

}

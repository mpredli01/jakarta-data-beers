package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * <p>BeerResources class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Path("beer")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BeerResources {

    @Inject
    BeerService beerService;

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link org.redlich.beers.BeerResponse} object
     */
    @GET
    @Path("/{id}")
    public BeerResponse findById(@PathParam("id") int id) {
        return beerService.findById(id)
                .map(BeerResponse::of)
                .orElseThrow(() -> new NotFoundException());
        }

    /**
     * <p>listBeers.</p>
     *
     * @return a {@link java.util.List} object
     */
    @GET
    public List<BeerResponse> listBeers(){
        return beerService.listBeers().map(BeerResponse::of).toList();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{brewer}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName){
        return beerService.listBeersByBrewer(brewerName)
                .map(BeerResponse::of).toList();
        }

    /**
     * <p>listBeersByBrewer.</p>
     *
     * @param brewerName a {@link java.lang.String} object
     * @param pageNum a long
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{brewer}/page/{pageNum}")
    public List<BeerResponse> listBeersByBrewer(@PathParam("brewer") String brewerName,
                                                @PathParam("pageNum") long pageNum) {
        PageRequest pageRequest = PageRequest.ofPage(pageNum)
                .size(5);

        Order<Beer> order = Order.by(_Beer.name.asc(),_Beer.id.asc());

        return beerService.listBeersByBrewer(brewerName, pageRequest, order).stream()
                .map(BeerResponse::of).toList();
        }

    /**
     * <p>add.</p>
     *
     * @param id a int
     * @param request a {@link org.redlich.beers.BeerRequest} object
     * @return a {@link org.redlich.beers.BeerResponse} object
     */
    @POST
    @Path("/{id}")
    public BeerResponse add(@PathParam("id") int id, BeerRequest request) {
        return BeerResponse.of(beerService.add(request.createBeer(id)));
        }

    /**
     * <p>remove.</p>
     *
     * @param id a int
     */
    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") int id){
        beerService.remove(id);
        }

    /**
     * <p>removeAll.</p>
     */
    @DELETE
    public void removeAll(){
        beerService.removeAll();
        }
    }

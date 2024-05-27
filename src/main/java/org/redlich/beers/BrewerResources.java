package org.redlich.beers;

import jakarta.data.Order;
import jakarta.data.page.PageRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * <p>BrewerResources class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@Path("brewer")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BrewerResources {

    @Inject
    BrewerService brewerService;

    /**
     * <p>findById.</p>
     *
     * @param id a int
     * @return a {@link org.redlich.beers.BrewerResponse} object
     */
    @GET
    @Path("/{id}")
    public BrewerResponse findById(@PathParam("id") int id) {
        return brewerService.findById(id)
                .map(BrewerResponse::of)
                .orElseThrow(() -> new NotFoundException());
        }

    /**
     * <p>listBrewers.</p>
     *
     * @return a {@link java.util.List} object
     */
    @GET
    public List<BrewerResponse> listBrewers() {
        return brewerService.listBrewers()
                .map(BrewerResponse::of)
                .toList();
        }

    /**
     * <p>listBrewerByName.</p>
     *
     * @param name a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{name}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name) {
        return brewerService.listBrewersByNameLike(name)
                .map(BrewerResponse::of)
                .toList();
        }

    /**
     * <p>listBrewerByName.</p>
     *
     * @param name a {@link java.lang.String} object
     * @param pageNum a long
     * @return a {@link java.util.List} object
     */
    @GET
    @Path("/brewer/{name}/page/{pageNum}")
    public List<BrewerResponse> listBrewerByName(@PathParam("name") String name,
                                                 @PathParam("pageNum") long pageNum) {
        PageRequest pageRequest = PageRequest.ofPage(pageNum)
                .size(5);
                
        Order<Brewer> order = Order.by(_Brewer.name.asc(),_Brewer.id.asc());
        
        return brewerService.listBrewersByNameLike(name, pageRequest, order)
                .stream().map(BrewerResponse::of).toList();
        }

    /**
     * <p>add.</p>
     *
     * @param id a int
     * @param request a {@link org.redlich.beers.BrewerRequest} object
     * @return a {@link org.redlich.beers.BrewerResponse} object
     */
    @POST
    @Path("/{id}")
    public BrewerResponse add(@PathParam("id") int id, BrewerRequest request) {
        var brewer = request.createBrewer(id);
        return BrewerResponse.of(brewerService.add(brewer));
        }

    /**
     * <p>remove.</p>
     *
     * @param id a int
     */
    @Path("/{id}")
    @DELETE
    public void remove(@PathParam("id") int id) {
        brewerService.remove(id);
        }

    /**
     * <p>removeAll.</p>
     */
    @DELETE
    public void removeAll() {
        brewerService.removeAll();
        }
    }

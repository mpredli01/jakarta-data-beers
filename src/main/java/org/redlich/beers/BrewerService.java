package org.redlich.beers;

import java.util.List;

import jakarta.data.Sort;
import jakarta.data.page.Page;
import jakarta.data.page.Pageable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("brewer")
@ApplicationScoped
public class BrewerService {

    @Inject
    BrewerRepository brewerRepository;

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(Brewer brewer) {

        try {
            brewerRepository.save(brewer);
            }
        catch (ConstraintViolationException x) {
            JsonArrayBuilder messages = Json.createArrayBuilder();
            for (ConstraintViolation<?> v : x.getConstraintViolations()) {
                messages.add(v.getMessage());
                }
            return messages.build().toString();
            }
        return "";
        }

    @DELETE
    @Path("/{id}")
    public void remove(@PathParam("id") int id) {
        brewerRepository.deleteByBrewerId(id);
        }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieve() {
        Iterable<Brewer> brewerRepositoryIterable = brewerRepository.findAll()::iterator;
        return brewerRepositoryToJsonArray(brewerRepositoryIterable);
        }

    @GET
    @Path("/brewer/{brewer}")
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveByBrewer(@PathParam("brewer") String brewer) {
        // List<Brewer> brewerRepositoryList = brewerRepository.findByBrewer(Brewer.fromString(brewer));
        List<Brewer> brewerRepositoryList = brewerRepository.findByBrewer(brewer);
        return brewerRepositoryToJsonArray(brewerRepositoryList);
        }

    @GET
    @Path("/brewer/{brewer}/page/{pageNum}")
    @Produces(MediaType.APPLICATION_JSON)
    public String retrieveByBrewer(@PathParam("brewer") String brewer, @PathParam("pageNum") long pageNum) {

        Pageable pageRequest = Pageable.ofSize(5)
                .page(pageNum)
                .sortBy(Sort.asc("name"), Sort.asc("id"));

        // Page<Brewer> page = brewerRepository.findByBrewer(Brewer.fromString(brewer), pageRequest);
        Page<Brewer> page = brewerRepository.findByBrewer(brewer, pageRequest);

        return brewerRepositoryToJsonArray(page);
        }

    @DELETE
    public void remove() {
        brewerRepository.deleteAll();
        }

    private String brewerRepositoryToJsonArray(Iterable<Brewer> brewerRepository) {
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Brewer c : brewerRepository) {
            JsonObject json = Json.createObjectBuilder()
                    .add("Name", c.getName())
                    .add("City", c.getCity())
                    .add("State",c.getState()).build();
            jab.add(json);
            }
        return jab.build().toString();
        }
    }

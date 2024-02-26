package org.redlich.beers;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * <p>BeerApplication class.</p>
 *
 * @author mpredli01
 * @version $Id: $Id
 */
@ApplicationPath("/db")
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class BeerApplication extends Application {
    @Inject
    BeerService beerService;

    @Inject
    BrewerService brewerService;

    @Inject
    @ConfigProperty(name = "message")
    String message;

    /**
     * <p>sayHello.</p>
     *
     * @return a {@link java.lang.String} object
     */
    @GET
    public String sayHello() {
        return this.message;
    }
}

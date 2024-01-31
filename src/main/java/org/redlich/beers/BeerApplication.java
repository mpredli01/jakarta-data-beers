package org.redlich.beers;

import jakarta.inject.Inject;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationPath("/db")
@Produces(MediaType.APPLICATION_JSON)
public class BeerApplication extends Application {
    @Inject
    BeerService beerService;

    @Inject
    BrewerService brewerService;

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @GET
    public String sayHello() {
        return this.message;
        }
    }

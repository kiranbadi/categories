package dropwizard.resources;

import dropwizard.client.Footer;
import dropwizard.service.FooterService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Path("/footer")
@Produces(MediaType.APPLICATION_JSON)
public class FooterResource {


    private final FooterService footerService;

    public FooterResource(FooterService footerService) {
        this.footerService = footerService;
    }

    @GET
    @Path("/{id}")
    public Response getFooterById(@PathParam("id") int id) {
        Footer footer = footerService.findFooterById(id);
        if (footer != null) {
            return Response.ok(footer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createFooter(Footer footer) {
        log.info("Creating footer: {}", footer);
        footerService.insertFooter(footer);
        return Response.status(Response.Status.CREATED).build();
    }
}

package dropwizard.resources;


import dropwizard.client.Category;
import dropwizard.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;


@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@NoArgsConstructor
public class CategoryResource {

    private CategoryService categoryService ;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GET
    @Path("/{id}")
    public Response getCategoryById(@PathParam("id") int id) {
        Category category = categoryService.findCategoryById(id);
        if (category != null) {
            return Response.ok(category).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{name}/{id}")
    public Response getCategoryByNameOrId(@PathParam("name") String name, @PathParam("id") int id) {
        Category category = categoryService.findCategoryByNameOrId(name, id);
        if (category != null) {
            return Response.ok(category).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}

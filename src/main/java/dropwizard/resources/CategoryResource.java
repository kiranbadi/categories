package dropwizard.resources;


import dropwizard.client.Category;
import dropwizard.service.CategoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.NoArgsConstructor;

import java.util.Optional;


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

    @POST
    @Path("/save")
    public Response saveCategory(Category category) {
        Category category1 = categoryService.saveCategory(category);
        if (category1 != null) {
            return Response.ok(category1).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/upsert")
    public Response upsertCategory(Category category) {
        Optional<Category> category1 = categoryService.upsertCategory(category);
        if (category1.isPresent()) {
            return Response.ok(category1).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DELETE
    @Path("/delete/{id}")
    public Response deleteCategory(@PathParam("id") int id) {
        categoryService.deleteCategory(id);
        return Response.ok().build();
    }

}

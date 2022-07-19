package br.com.estudosgovbr.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.estudosgovbr.data.dto.CategoriaDTO;
import br.com.estudosgovbr.services.CategoriaService;

@Path("/categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource 
{
    @Inject
    CategoriaService categoriaService;

    @GET
    public List<CategoriaDTO> getAll()
    {
        return this.categoriaService.getAllCategorias();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id)
    {
        return Response.status(Status.FOUND).entity(this.categoriaService.getById(id)).build();
    }

    @POST
    public Response post(@RequestBody CategoriaDTO categoriaDTO)
    {
        return Response.status(Status.CREATED).entity(this.categoriaService.postCategoria(categoriaDTO)).build();
    }

    @DELETE
    @Path("{id}")
    public Boolean delete(@PathParam("id") Integer id)
    {
        return this.categoriaService.deleteCategoria(id);
    }

    @PUT
    @Path("/toggleAtivo")
    public Boolean toggleAtivo(@RequestBody Integer id)
    {
        return this.categoriaService.toggle(id);
    }

}

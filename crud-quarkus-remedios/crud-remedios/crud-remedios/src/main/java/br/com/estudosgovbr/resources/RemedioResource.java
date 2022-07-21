package br.com.estudosgovbr.resources;

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

import br.com.estudosgovbr.dto.RemedioDTO;
import br.com.estudosgovbr.services.RemedioService;

@Path("/remedio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RemedioResource 
{
    @Inject
    RemedioService remedioService;

    @GET
    public List<RemedioDTO> getAll()
    {
        return remedioService.getAllByNomeOrdered();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) throws Exception
    {
        return Response.status(Status.FOUND).entity(this.remedioService.getById(id)).build();
    }

    @POST
    public Response post(@RequestBody RemedioDTO remedioDTO) 
    {
        return Response.status(Status.CREATED).entity(this.remedioService.post(remedioDTO)).build();
    }

    @PUT
    public Response updateRemedio(@RequestBody RemedioDTO remedioDTO)
    {
        return Response.status(Status.OK).entity(this.remedioService.update(remedioDTO)).build();
    }

    @DELETE
    @Path("{id}")
    public boolean deleteRemedio(@PathParam("id") Integer id)
    {
        return this.remedioService.delete(id);
    }

    
}

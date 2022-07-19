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

import br.com.estudosgovbr.data.dto.NotaDTO;
import br.com.estudosgovbr.services.NotaService;

@Path("/notas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotaResource 
{
    @Inject
    NotaService notaService;

    @GET
    public List<NotaDTO> getAll()
    {
        return notaService.getAllNotas();
    }


    @POST
    public Response postNota(@RequestBody NotaDTO dto)
    {
        return Response.status(Status.CREATED).entity(this.notaService.post(dto)).build();
    }

    @PUT
    public Response updateNota(@RequestBody NotaDTO dto)    
    {
        return Response.status(Status.OK).entity(this.notaService.update(dto)).build();
    }

    @DELETE
    @Path("{id}")
    public Boolean delete(@PathParam("id") Integer id)
    {
        return this.notaService.deleteNota(id);
    }

}

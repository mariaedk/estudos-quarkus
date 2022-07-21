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

import br.com.estudosgovbr.dto.PacienteDTO;
import br.com.estudosgovbr.services.PacienteService;

@Path("/paciente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource 
{
    @Inject
    PacienteService pacienteService;

    @GET
    public List<PacienteDTO> getAll()
    {
        return pacienteService.getAllByNomeOrdered();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) throws Exception
    {
        return Response.status(Status.FOUND).entity(this.pacienteService.getById(id)).build();
    }

    @GET
    @Path("/status/{status}")
    public List<PacienteDTO> getByStatus(@PathParam("status") String status)
    {
        return this.pacienteService.getPacienteByStatus(status);
    }

    @POST
    public Response postPaciente(@RequestBody PacienteDTO pacienteDTO)
    {
        return Response.status(Status.CREATED).entity(this.pacienteService.post(pacienteDTO)).build();
    }

    @PUT
    public Response updatePaciente(@RequestBody PacienteDTO pacienteDTO)
    {
        return Response.status(Status.OK).entity(this.pacienteService.update(pacienteDTO)).build();
    }

    @DELETE
    @Path("{id}")
    public boolean deletePaciente(@PathParam("id") Integer id)
    {
        return this.pacienteService.delete(id);
    }

}

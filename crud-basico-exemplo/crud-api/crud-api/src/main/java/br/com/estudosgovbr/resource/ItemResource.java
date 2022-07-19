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
import br.com.estudosgovbr.dto.ItemDto;
import br.com.estudosgovbr.service.ItemService;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource 
{
    @Inject
	ItemService itemService;

	@GET
	public List<ItemDto> get(){
		return itemService.get();
	}

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id)
    {
        return Response.status(Status.FOUND).entity(this.itemService.getById(id)).build();
    }
	
	@POST
	public Response create(@RequestBody ItemDto item)
	{
		return Response.status(Status.CREATED).entity(this.itemService.create(item)).build();
	}
	
	@PUT
	public Response update(@RequestBody ItemDto item)
	{
		return Response.status(Status.OK).entity(this.itemService.update(item)).build();
	}
	
	@DELETE
	@Path("{id}")
	public boolean delete(@PathParam("id") Long id)
	{
		return itemService.delete(id);
	}
}

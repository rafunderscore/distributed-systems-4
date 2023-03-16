package myApp;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/module")
public class ModuleResource {

	@GET
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	public List<Module> getModules(){
		return ModuleDAO.instance.getModules();
	}

	@GET
	@Produces( {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON} )
	@Path("{moduleId}")
	public Module getModule(@PathParam("moduleId") String id) {
		return ModuleDAO.instance.getModule(Integer.parseInt(id));
	}
}

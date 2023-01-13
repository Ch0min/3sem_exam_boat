package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datafacades.BoatFacade;
import dtofacades.BoatDTOFacade;
import dtos.BoatDTO;
import errorhandling.API_Exception;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;

@Path("boats")
public class BoatResource {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private BoatDTOFacade boatDTOFacade = BoatDTOFacade.getInstance(EMF);
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();


    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllBoats() throws API_Exception {
        return Response.ok().entity(GSON.toJson(boatDTOFacade.getAllBoats()))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
    }

    @GET
    @Path("/{harbourName}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getBoatsByHarbour(@PathParam("harbourName") String harbourName) throws API_Exception {
        return Response.ok().entity(GSON.toJson(boatDTOFacade.getBoatsByHarbour(harbourName)))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createBoat(String content) throws API_Exception {
        BoatDTO boatDTO = GSON.fromJson(content, BoatDTO.class);
        BoatDTO newBoatDTO = boatDTOFacade.createBoat(boatDTO);
        return Response.ok().entity(GSON.toJson(newBoatDTO))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
    }

    @POST
    @Path("/assign/{boatID}/{harbourID}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addUserToTrainingSession(@PathParam("boatID") int boatID,
                                             @PathParam("harbourID") int harbourID) throws API_Exception {
        return Response.ok().entity(GSON.toJson(boatDTOFacade.assignBoatToHarbour(boatID, harbourID)))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
    }

    @PUT
    @Path("/{boatID}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateBoat(@PathParam("boatID") int boatID, String content) throws API_Exception {
        BoatDTO boatDTO = GSON.fromJson(content, BoatDTO.class);
        boatDTO.setBoatID(boatID);
        BoatDTO updatedBoatDTO = boatDTOFacade.updateBoat(boatDTO);
        return Response.ok().entity(GSON.toJson(updatedBoatDTO))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
     }

    @DELETE
    @Path("{boatID}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteBoat(@PathParam("boatID") int boatID) throws API_Exception {
        BoatDTO deletedBoat = boatDTOFacade.deleteBoat(boatID);
        return Response.ok().entity(GSON.toJson(deletedBoat))
                .type(MediaType.APPLICATION_JSON_TYPE.withCharset(StandardCharsets.UTF_8.name())).build();
    }
}

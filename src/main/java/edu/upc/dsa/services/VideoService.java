package edu.upc.dsa.services;

import edu.upc.dsa.models.Video;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Api(value = "/media", description = "Servicio de videos de soporte")
@Path("/media")
public class VideoService {

    final static Logger logger = Logger.getLogger(VideoService.class);

    public VideoService() {
        // Constructor vacío
    }

    @GET
    @ApiOperation(value = "Obtener lista de videos", notes = "Devuelve todos los videos de soporte disponibles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de videos obtenida", response = Video.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Error interno del servidor")
    })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVideos() {
        try {
            List<Video> videos = new ArrayList<>();

            videos.add(new Video("https://www.youtube.com/watch?v=M6pQ63ssBKQ", "FAQs"));
            videos.add(new Video("https://www.youtube.com/watch?v=ox7WSYmAm5A", "No recuerdo mi contraseña"));
            videos.add(new Video("https://www.youtube.com/watch?v=dcOMFYfVqqc", "¿Fecha para la versión en iOS?"));
            videos.add(new Video("https://www.youtube.com/watch?v=SRfU9meXlC8", "¿ Cómo jugar?"));

            logger.info("Devolviendo " + videos.size() + " videos");
            return Response.status(200).entity(videos).build();

        } catch (Exception e) {
            logger.error("Error al obtener videos", e);
            return Response.status(500).entity("{\"error\":\"Error al obtener videos\"}").build();
        }
    }
}
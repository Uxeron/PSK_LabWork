package com.gab.psk_project.rest;

import com.gab.psk_project.entities.Computer;
import com.gab.psk_project.entities.Part;
import com.gab.psk_project.persistence.PartsDAO;
import com.gab.psk_project.rest.contracts.PartDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@ApplicationScoped
@Path("/parts")
public class PartsController {

    @Inject
    @Setter @Getter
    private PartsDAO partsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Part part = partsDAO.findOne(id);
        if (part == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(partToData(part)).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(PartDto partData) {
        Part newPart = new Part();
        newPart.setName(partData.getName());
        partsDAO.persist(newPart);
        return Response.ok(partToData(newPart)).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long partId,
            PartDto partData) {
        try {
            Part existingPart = partsDAO.findOne(partId);
            if (existingPart == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            try {
                Thread.sleep(3000); // Simulate intensive work
            } catch (InterruptedException e) {
            }

            existingPart.setName(partData.getName());
            partsDAO.update(existingPart);
            return Response.ok(partToData(existingPart)).build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    private PartDto partToData(Part part) {
        PartDto partDto = new PartDto();
        partDto.setName(part.getName());
        partDto.setId(part.getId());

        ArrayList<String> computers = new ArrayList<String>();
        if (part.getComputers() != null) {
            for (Computer computer: part.getComputers()) {
                computers.add(computer.getName());
            }
        }

        partDto.setComputers(computers);
        return partDto;
    }
}

package com.gab.psk_project.usercases;

import com.gab.psk_project.entities.Computer;
import com.gab.psk_project.entities.Part;
import com.gab.psk_project.persistence.ComputersDAO;
import com.gab.psk_project.persistence.PartsDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class ExistingPart implements Serializable {

    @Inject
    private ComputersDAO computersDAO;

    @Inject
    private PartsDAO partsDAO;

    @Getter
    @Setter
    private Computer computer;

    @Getter
    @Setter
    private Part part;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long computerId = Long.parseLong(requestParameters.get("computerId"));
        Long partId = Long.parseLong(requestParameters.get("partId"));
        this.computer = computersDAO.findOne(computerId);
        this.part = partsDAO.findOne(partId);
    }

    @Transactional
    //@LoggedInvocation
    public void addPart() {
        part.getComputers().add(computer);
        computer.getParts().add(part);
    }

    @Transactional
    //@LoggedInvocation
    public void removePart() {
        part.getComputers().remove(computer);
        computer.getParts().remove(part);
    }
}
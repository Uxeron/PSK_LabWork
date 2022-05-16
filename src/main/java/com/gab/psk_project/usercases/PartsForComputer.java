package com.gab.psk_project.usercases;

import com.gab.psk_project.entities.Computer;
import com.gab.psk_project.entities.Part;
import com.gab.psk_project.entities.Store;
import com.gab.psk_project.persistence.ComputersDAO;
import com.gab.psk_project.persistence.PartsDAO;
import com.gab.psk_project.persistence.StoresDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Model
public class PartsForComputer implements Serializable {

    @Inject
    private ComputersDAO computersDAO;

    @Inject
    private PartsDAO partsDAO;

    @Getter
    @Setter
    private Computer computer;

    @Getter @Setter
    private Part partToCreate = new Part();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long computerId = Long.parseLong(requestParameters.get("computerId"));
        this.computer = computersDAO.findOne(computerId);
    }

    @Transactional
    //@LoggedInvocation
    public void createPart() {
        partToCreate.setComputers(Arrays.asList(this.computer));
        partsDAO.persist(partToCreate);
    }
}
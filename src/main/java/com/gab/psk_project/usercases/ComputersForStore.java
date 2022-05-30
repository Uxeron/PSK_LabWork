package com.gab.psk_project.usercases;

import com.gab.psk_project.entities.Computer;
import com.gab.psk_project.entities.Store;
import com.gab.psk_project.interceptors.LoggedInvocation;
import com.gab.psk_project.persistence.ComputersDAO;
import com.gab.psk_project.persistence.StoresDAO;
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
public class ComputersForStore implements Serializable {

    @Inject
    private StoresDAO storesDAO;

    @Inject
    private ComputersDAO computersDAO;

    @Getter
    @Setter
    private Store store;

    @Getter @Setter
    private Computer computerToCreate = new Computer();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long storeId = Long.parseLong(requestParameters.get("storeId"));
        this.store = storesDAO.findOne(storeId);
    }

    @Transactional
    @LoggedInvocation
    public void createComputer() {
        computerToCreate.setStore(this.store);
        computersDAO.persist(computerToCreate);
    }
}
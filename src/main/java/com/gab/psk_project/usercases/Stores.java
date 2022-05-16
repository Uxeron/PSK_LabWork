package com.gab.psk_project.usercases;

import com.gab.psk_project.entities.Store;
import com.gab.psk_project.persistence.StoresDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Stores {

    @Inject
    private StoresDAO storesDAO;

    @Getter
    @Setter
    private Store storeToCreate = new Store();

    @Getter
    private List<Store> allStores;

    @PostConstruct
    public void init(){
        loadAllStores();
    }

    @Transactional
    public void createStore(){
        this.storesDAO.persist(storeToCreate);
    }

    private void loadAllStores(){
        this.allStores = storesDAO.loadAll();
    }
}

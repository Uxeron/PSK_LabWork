package com.gab.psk_project.persistence;

import com.gab.psk_project.entities.Store;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StoresDAO {
    @Inject
    private EntityManager em;

    public List<Store> loadAll() {
        return em.createQuery("select s from Store as s", Store.class).getResultList();
    }

    public void persist(Store store){
        this.em.persist(store);
    }

    public Store findOne(Long id) {
        return em.find(Store.class, id);
    }
}

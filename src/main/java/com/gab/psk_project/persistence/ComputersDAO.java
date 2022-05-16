package com.gab.psk_project.persistence;

import com.gab.psk_project.entities.Computer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ComputersDAO {
    @Inject
    private EntityManager em;

    public List<Computer> loadAll() {
        return em.createQuery("select c from Computer as c", Computer.class).getResultList();
    }

    public void persist(Computer computer){
        this.em.persist(computer);
    }

    public Computer findOne(Long id) {
        return em.find(Computer.class, id);
    }
}

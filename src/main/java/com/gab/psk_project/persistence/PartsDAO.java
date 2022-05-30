package com.gab.psk_project.persistence;

import com.gab.psk_project.entities.Part;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PartsDAO {
    @Inject
    private EntityManager em;

    public List<Part> loadAll() {
        return em.createQuery("select c from Part as c", Part.class).getResultList();
    }

    public void persist(Part part){
        this.em.persist(part);
    }

    public Part findOne(Long id) {
        return em.find(Part.class, id);
    }

    public Part update(Part part) {
        Part updated_part =  em.merge(part);
        em.flush();
        return updated_part;
    }
}

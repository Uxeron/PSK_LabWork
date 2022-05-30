package com.gab.psk_project.usercases;

import com.gab.psk_project.entities.Computer;
import com.gab.psk_project.persistence.ComputersDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateComputerDetails  implements Serializable {

    private Computer computer;

    @Inject
    private ComputersDAO computersDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateComputerDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long computerId = Long.parseLong(requestParameters.get("computerId"));
        this.computer = computersDAO.findOne(computerId);
    }

    @Transactional
    // @LoggedInvocation
    public String updateComputerName() {
        try{
            computersDAO.update(this.computer);
        } catch (OptimisticLockException e) {
            return "/parts.xhtml?faces-redirect=true&computerId=" + this.computer.getId() + "&error=optimistic-lock-exception";
        }
        return "parts.xhtml?faces-redirect=true&computerId=" + this.computer.getId();
    }
}

package com.gab.psk_project.usercases;

import com.gab.psk_project.services.ComputerNameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateComputerName implements Serializable {
    @Inject
    ComputerNameGenerator computerNameGenerator;

    private CompletableFuture<String> computerNameGenerationTask = null;

    // @LoggedInvocation
    public String generateNewComputerName() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        computerNameGenerationTask = CompletableFuture.supplyAsync(() -> computerNameGenerator.generateComputerName());

        return  "/computers.xhtml?faces-redirect=true&storeId=" + requestParameters.get("storeId");
    }

    public boolean isNameGenerationRunning() {
        return computerNameGenerationTask != null && !computerNameGenerationTask.isDone();
    }

    public String getNameGenerationStatus() throws ExecutionException, InterruptedException {
        if (computerNameGenerationTask == null) {
            return null;
        } else if (isNameGenerationRunning()) {
            return "Name generation in progress";
        }
        return "Suggested computer name: " + computerNameGenerationTask.get() + " ";
    }
}
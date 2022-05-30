package com.gab.psk_project.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class ComputerNameGenerator {
    public String generateComputerName() {
        try {
            Thread.sleep(3000); // Intense generation work happening
        } catch (InterruptedException e) {
        }
        return "Computer_" + (new Random().nextInt(100));
    }
}

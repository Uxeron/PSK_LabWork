package com.gab.psk_project.rest.contracts;

import com.gab.psk_project.entities.Computer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PartDto {

    private Long id;

    private String Name;

    private List<String> Computers;
}

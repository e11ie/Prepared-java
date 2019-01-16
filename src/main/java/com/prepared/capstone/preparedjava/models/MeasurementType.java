package com.prepared.capstone.preparedjava.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MeasurementType {

    @Id
    @GeneratedValue // Together these mean that id is a unique key id
    private int id;

    @NotNull
    @Size(min=1, max=60, message = "Name must not be empty")
    private String name;

    @OneToMany
    @JoinColumn(name = "type_id")
    private List<Unit> units = new ArrayList<>();

    public MeasurementType() {}

    public MeasurementType(String name) {
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Unit> getUnits() { return units; }

    public void setUnits(List<Unit> units) { this.units = units; }
}

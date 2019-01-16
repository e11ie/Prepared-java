package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.MeasurementType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddUnitForm {

    private Iterable<MeasurementType> types;

    @NotNull
    @Size(min=3, max=60)
    private String name;

    @Size(min=1, max=10)
    private String abbrev;

    @NotNull
    private int typeId;

    public AddUnitForm() {}

    public AddUnitForm(Iterable<MeasurementType> types) {
        this.types = types;
    }

    public Iterable<MeasurementType> getTypes() { return types; }

    public void setTypes(Iterable<MeasurementType> types) { this.types = types; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTypeId() { return typeId; }

    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getAbbrev() { return abbrev; }

    public void setAbbrev(String abbrev) { this.abbrev = abbrev; }
}

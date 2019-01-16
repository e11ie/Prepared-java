package com.prepared.capstone.preparedjava.models.forms;

import com.prepared.capstone.preparedjava.models.MeasurementType;
import com.prepared.capstone.preparedjava.models.Unit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditUnitForm {

    private Unit unit;
    private Iterable<MeasurementType> types;

    @NotNull
    @Size(min=3, max=60)
    private String name;

    @Size(min=1, max=10)
    private String abbrev;

    @NotNull
    private int typeId;

    @NotNull
    private int unitId;

    public EditUnitForm() {}

    public EditUnitForm(Iterable<MeasurementType> types, Unit unit) {
        this.unit = unit;
        this.types = types;
    }

    public Unit getUnit() { return unit; }

    public void setUnit(Unit unit) { this.unit = unit; }

    public int getUnitId() { return unitId; }

    public void setUnitId(int unitId) { this.unitId = unitId; }

    public Iterable<MeasurementType> getTypes() { return types; }

    public void setTypes(Iterable<MeasurementType> types) { this.types = types; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getTypeId() { return typeId; }

    public void setTypeId(int typeId) { this.typeId = typeId; }

    public String getAbbrev() { return abbrev; }

    public void setAbbrev(String abbrev) { this.abbrev = abbrev; }
}

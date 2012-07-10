package com.worthsoln.patientview.uktransplant;

public class UktCode {

    private int id;
    private String uktcode;
    private String description;

    public UktCode() {
    }

    public UktCode(String uktcode, String description) {
        this.uktcode = uktcode;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUktcode() {
        return uktcode;
    }

    public void setUktcode(String uktcode) {
        this.uktcode = uktcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UktCode)) {
            return false;
        }
        final UktCode uktCode = (UktCode) o;
        if (description != null ? !description.equals(uktCode.description) : uktCode.description != null) {
            return false;
        }
        if (uktcode != null ? !uktcode.equals(uktCode.uktcode) : uktCode.uktcode != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        result = (uktcode != null ? uktcode.hashCode() : 0);
        result = 29 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

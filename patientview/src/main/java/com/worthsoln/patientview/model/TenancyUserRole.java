package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *  Allow users to have mutiple entries (i.e. different roles in different tenancies) in the patient view system
 */
@Entity
public class TenancyUserRole extends BaseModel {


    @ManyToOne
    @JoinColumn(name = "tenancy_id")
    private Tenancy tenancy;

    @Column(nullable = false)
    private Long userFkId;

    @Column(nullable = false)
    private String role;

    // unit code?

    public Tenancy getTenancy() {
        return tenancy;
    }

    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }

    public Long getUserFkId() {
        return userFkId;
    }

    public void setUserFkId(Long userFkId) {
        this.userFkId = userFkId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

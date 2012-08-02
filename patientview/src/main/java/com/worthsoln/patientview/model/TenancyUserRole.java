package com.worthsoln.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *  Allow users to have multiple entries (i.e. different roles in different tenancies) in the patient view system
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames={"tenancy_id", "user_id", "role"}))
public class TenancyUserRole extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "tenancy_id")
    private Tenancy tenancy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String role;

    public Tenancy getTenancy() {
        return tenancy;
    }

    public void setTenancy(Tenancy tenancy) {
        this.tenancy = tenancy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

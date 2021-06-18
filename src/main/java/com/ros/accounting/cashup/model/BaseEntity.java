package com.ros.accounting.cashup.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    private boolean status;

    @Temporal(value = TemporalType.DATE)
    private Date createdDate;

    private String createdBy;

    @Temporal(value = TemporalType.DATE)
    private Date lastModifiedDate;

    private String updatedBy;

    public void addMetaData() {
        this.setStatus(true);
        this.setCreatedDate(new Date());
        this.setCreatedBy("user");
    }

    public void editMetaData() {
        this.setStatus(true);
        this.setUpdatedBy("user");
        this.setLastModifiedDate(new Date());
    }
}

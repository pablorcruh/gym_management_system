package ec.com.pablorcruh.gym_management_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    private UUID id;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date(System.currentTimeMillis());
        this.id = UUID.randomUUID();
        this.active = true;
    }
}

package epam.rd.traydakalo.entity;

import javax.persistence.*;
import java.io.Externalizable;
import java.time.ZonedDateTime;

@Entity
public class Claim /*extends Externalizable*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Boolean isDone;
    private ZonedDateTime claimedAt;

    public Claim() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public ZonedDateTime getClaimedAt() {
        return claimedAt;
    }

    public void setClaimedAt(ZonedDateTime claimedAt) {
        this.claimedAt = claimedAt;
    }
}

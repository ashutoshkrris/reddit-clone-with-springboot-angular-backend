package in.ashutoshkrris.reddit.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class DateEntity {

    @Column(nullable = false, updatable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @PrePersist
    private void onCreate() {
        createdAt = System.currentTimeMillis();
        updatedAt = System.currentTimeMillis();
    }

    @PreUpdate
    private void onUpdate() {
        updatedAt = System.currentTimeMillis();
    }

}

package mindTrace.local.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import mindTrace.local.Enums.Status;
import mindTrace.local.Utils.BaseEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Project extends BaseEntity {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
}

package mindTrace.local.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mindTrace.local.Utils.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class File extends BaseEntity {
    private String name;
    private float size;
    private String extension;

    @ManyToOne
    private Project project;
}

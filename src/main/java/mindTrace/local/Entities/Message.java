package mindTrace.local.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import mindTrace.local.Enums.Sender;
import mindTrace.local.Utils.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message extends BaseEntity {
    private String content;
    @Enumerated(EnumType.STRING)
    private Sender sender;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

}

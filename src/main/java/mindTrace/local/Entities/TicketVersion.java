package mindTrace.local.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;
import mindTrace.local.Enums.Status;
import mindTrace.local.Utils.BaseEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class TicketVersion extends BaseEntity {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Ticket ticket;
}

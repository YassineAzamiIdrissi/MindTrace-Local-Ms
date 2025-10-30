package mindTrace.local.Entities;


import jakarta.persistence.*;
import lombok.*;
import mindTrace.local.Enums.Status;
import mindTrace.local.Utils.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ticket extends BaseEntity {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "ticket")
    private List<Ticket> subTickets;

    @ManyToOne
    private Ticket ticket;

    @OneToMany(mappedBy = "ticket")
    private List<TicketVersion> versions;
}

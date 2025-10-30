package mindTrace.local.Entities;


import jakarta.persistence.*;
import lombok.*;
import mindTrace.local.Enums.Status;
import mindTrace.local.Utils.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    private Admin creator;

    @OneToMany(mappedBy = "project")
    private List<File> associatedFiles;

    @OneToMany(mappedBy = "project")
    private List<Message> messages;

    @OneToMany(mappedBy = "project")
    private List<Ticket> tickets;
}

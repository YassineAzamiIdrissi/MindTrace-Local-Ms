package mindTrace.local.Entities;

import jakarta.persistence.*;
import lombok.*;
import mindTrace.local.Utils.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User extends BaseEntity {
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String email;
    private String password;
    private LocalDateTime birthDate;


    @OneToMany(mappedBy = "user")
    private List<Message> messages;

    @OneToMany(mappedBy = "user")
    private List<Ticket> createdTickets;


    public String getFullName() {
        return firstname +" "+ lastname;
    }
}

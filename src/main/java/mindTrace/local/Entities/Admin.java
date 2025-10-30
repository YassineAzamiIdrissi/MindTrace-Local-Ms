package mindTrace.local.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Admin extends BaseEntity {
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String email;
    private String password;
    private LocalDateTime birthDate;

    @OneToMany(mappedBy = "creator")
    private List<Project> projects;

    public String getFullName() {
        return firstname +" "+ lastname;
    }
}

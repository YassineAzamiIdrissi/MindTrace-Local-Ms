package mindTrace.local.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import mindTrace.local.Utils.BaseEntity;

import java.time.LocalDateTime;

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
}

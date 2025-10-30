package mindTrace.local.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// @Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String firstname;
    private String lastname;
    private String password;
    private LocalDateTime birthDate;
    @Column(unique=true)
    private String email;

}

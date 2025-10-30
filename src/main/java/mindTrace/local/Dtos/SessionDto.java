package mindTrace.local.Dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
public class SessionDto {
    private String firstname;
    private String lastname;
    private String email;
}

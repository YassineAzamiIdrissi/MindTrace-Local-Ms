package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ModificationRespDTO {
    private Integer ticketId;
    private String ticketName;
    private String description;
}

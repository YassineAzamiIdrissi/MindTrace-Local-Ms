package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mindTrace.local.Enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketResponseDTO {
    private Integer id;
    private String name;
    private String description;
    private Status status;
    private String creator;
    private String project;
}

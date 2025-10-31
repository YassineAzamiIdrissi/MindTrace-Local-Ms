package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketReqDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer projectId;
    private Integer userId;
}

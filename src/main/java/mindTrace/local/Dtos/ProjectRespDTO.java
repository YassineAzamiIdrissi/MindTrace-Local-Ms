package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectRespDTO {
    private Integer id;
    private String creator;
    private String name;
    private String description;
}

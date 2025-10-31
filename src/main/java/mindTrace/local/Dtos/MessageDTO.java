package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mindTrace.local.Enums.Sender;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageDTO {
    private String content;
    private Integer userId;
    private Integer projectId;
    private LocalDateTime sentAt;
    private Sender sender;
}

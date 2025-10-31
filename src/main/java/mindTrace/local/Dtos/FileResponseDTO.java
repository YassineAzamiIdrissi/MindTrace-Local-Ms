package mindTrace.local.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileResponseDTO {
    private String name;
    private long size;
    private String extension;
    private String url;
    private String projectName;
    private LocalDateTime createdAt;
}

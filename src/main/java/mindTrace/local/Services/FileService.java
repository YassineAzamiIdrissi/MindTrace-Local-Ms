package mindTrace.local.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String saveFile(MultipartFile file,Integer projectId) throws IOException;
}

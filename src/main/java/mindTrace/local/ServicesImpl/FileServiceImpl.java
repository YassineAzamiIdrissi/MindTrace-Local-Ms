package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Constants.ExceptionsMessages;
import mindTrace.local.Entities.File;
import mindTrace.local.Entities.Project;
import mindTrace.local.Repositories.FileRepository;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Services.FileService;
import mindTrace.local.Services.Implemented.R2StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static mindTrace.local.Constants.ExceptionsMessages.PROJECT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ProjectRepository projectRepository;
    private final R2StorageService r2StorageService;

    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }


    @Override
    public String saveFile(MultipartFile file,Integer projectId) throws IOException {
        Project project = projectRepository.findById(projectId).
                orElseThrow(()-> new RuntimeException(PROJECT_NOT_FOUND));
        String name = file.getOriginalFilename();
        String ext = getFileExtension(name);
        long size = file.getSize();
        String remoteUrl = r2StorageService.uploadFile(
                file.getOriginalFilename(),
                file.getBytes(),
                file.getContentType()
        );
        File local = File.builder().name(name).
                size(size).
                project(project).
                url(remoteUrl).
                extension(ext).
                build();
        fileRepository.save(local);
        return remoteUrl;
    }
}

package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/docs")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload/{projectId}")
    public ResponseEntity<String> uploadFile(
            @PathVariable Integer projectId,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            String fileUrl = fileService.saveFile(file, projectId);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erreur lors de l'enregistrement du fichier : " + e.getMessage());
        }
    }
}

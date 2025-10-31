package mindTrace.local.Controllers;

import mindTrace.local.Services.Implemented.R2StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {
    private final R2StorageService r2Service;

    public Controller(R2StorageService r2Service) {
        this.r2Service = r2Service;
    }

    @PostMapping("")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        System.out.println("TROLOLOLOLOL");
        return r2Service.uploadFile(
                file.getOriginalFilename(),
                file.getBytes(),
                file.getContentType()
        );
    }
}

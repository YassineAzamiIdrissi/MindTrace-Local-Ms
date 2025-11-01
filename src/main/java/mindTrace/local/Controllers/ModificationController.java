package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.ModificationRespDTO;
import mindTrace.local.Services.ModificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/modifications")
public class ModificationController {

    private final ModificationService modificationService;

    @GetMapping("/latest")
    public ResponseEntity<List<ModificationRespDTO>> getLastModifications() {
        return ResponseEntity.ok(modificationService.getLastModifications());
    }
}

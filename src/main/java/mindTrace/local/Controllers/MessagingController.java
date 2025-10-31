package mindTrace.local.Controllers;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.MessageDTO;
import mindTrace.local.Services.MessagingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessagingController {

    private final MessagingService messagingService;

    @PostMapping
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO message) {
        MessageDTO savedMessage = messagingService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getConversation(
            @RequestParam Integer projectId,
            @RequestParam Integer userId) {
        List<MessageDTO> messages = messagingService.getConversation(projectId, userId);
        return ResponseEntity.ok(messages);
    }

}

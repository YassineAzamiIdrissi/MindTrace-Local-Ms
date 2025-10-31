package mindTrace.local.Services;


import mindTrace.local.Dtos.MessageDTO;

import java.util.List;

public interface MessagingService {
    MessageDTO saveMessage(MessageDTO message);
    List<MessageDTO> getConversation(Integer projectId, Integer userId);
}

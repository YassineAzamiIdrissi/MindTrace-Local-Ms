package mindTrace.local.Services;


import mindTrace.local.Dtos.MessageDTO;

public interface MessagingService {
    MessageDTO saveMessage(MessageDTO message);
}

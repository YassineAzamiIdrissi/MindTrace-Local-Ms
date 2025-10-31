package mindTrace.local.ServicesImpl;

import lombok.RequiredArgsConstructor;
import mindTrace.local.Dtos.MessageDTO;
import mindTrace.local.Entities.Message;
import mindTrace.local.Entities.Project;
import mindTrace.local.Entities.User;
import mindTrace.local.GenMapper.Mapper;
import mindTrace.local.Repositories.MessagesRepository;
import mindTrace.local.Repositories.ProjectRepository;
import mindTrace.local.Repositories.UserRepository;
import mindTrace.local.Services.MessagingService;
import org.springframework.stereotype.Service;

import java.util.List;

import static mindTrace.local.Constants.ExceptionsMessages.PROJECT_NOT_FOUND;
import static mindTrace.local.Constants.ExceptionsMessages.USER_NOT_FOUND;
import static mindTrace.local.Enums.Sender.CHATBOT;
import static mindTrace.local.Enums.Sender.USER;

@RequiredArgsConstructor
@Service
public class MessagingServiceImpl implements MessagingService {

    private final MessagesRepository messagesRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Override
    public MessageDTO saveMessage(MessageDTO message) {
        Message newMessage = new Message();
        Project project = projectRepository.findById(message.getProjectId()).
                orElseThrow(() -> new RuntimeException(PROJECT_NOT_FOUND));
        newMessage.setProject(project);
        if(message.getUserId() == null || message.getUserId() == 0) {
            newMessage.setContent(message.getContent());
            newMessage.setSender(CHATBOT);
        } else {
            newMessage.setContent(message.getContent());
            newMessage.setSender(USER);
            User connected = userRepository.findById(message.getUserId()).
                    orElseThrow(()-> new RuntimeException(USER_NOT_FOUND));
            newMessage.setUser(connected);
        }
        message.setSentAt(messagesRepository.save(newMessage).getCreatedDate());
        return message;
    }

    @Override
    public List<MessageDTO> getConversation
            (Integer projectId, Integer userId) {
        List<Message> messages = messagesRepository.
                findAllByUserIdAndProjectIdOrderByCreatedDateDesc(userId,
                        projectId);
        return messages.stream().
                map(Mapper::fromEntityToMessageDTO).
                toList();
    }
}

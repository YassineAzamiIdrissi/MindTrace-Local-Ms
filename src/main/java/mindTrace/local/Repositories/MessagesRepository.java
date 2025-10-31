package mindTrace.local.Repositories;

import mindTrace.local.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByUserIdAndProjectIdOrderByCreatedDateDesc
            (Integer userId, Integer projectId);
}

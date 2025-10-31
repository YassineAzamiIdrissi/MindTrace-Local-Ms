package mindTrace.local.Repositories;

import mindTrace.local.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message,Integer> {
}

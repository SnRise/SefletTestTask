package ru.seflet.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seflet.chat.domain.Chat;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
//    public List<Chat>
}

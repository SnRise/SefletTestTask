package ru.seflet.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seflet.chat.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {


}

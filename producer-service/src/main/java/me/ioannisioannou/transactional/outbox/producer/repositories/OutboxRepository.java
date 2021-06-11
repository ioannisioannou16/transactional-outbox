package me.ioannisioannou.transactional.outbox.producer.repositories;

import me.ioannisioannou.transactional.outbox.producer.entities.Outbox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Page<Outbox> findAllByOrderByIdAsc(Pageable pageable);
}

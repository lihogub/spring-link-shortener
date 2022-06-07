package ru.lihogub.linkshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.lihogub.linkshortener.entity.Request;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM requests WHERE alias_id = :alias", nativeQuery = true)
    List<Request> findAllByAlias(String alias);
}

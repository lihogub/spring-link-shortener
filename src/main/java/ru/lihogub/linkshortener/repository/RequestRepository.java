package ru.lihogub.linkshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.lihogub.linkshortener.entity.Request;
import ru.lihogub.linkshortener.entity.RequestDetails;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "SELECT * FROM requests WHERE alias_id = :alias", nativeQuery = true)
    List<Request> findAllByAlias(String alias);

    @Modifying(flushAutomatically = true)
    @Query(value = "INSERT INTO requests (alias_id, date, ip) VALUES (:alias, :#{#details.date}, :#{#details.ip})", nativeQuery = true)
    void insertByAlias(String alias, RequestDetails details);
}

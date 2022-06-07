package ru.lihogub.linkshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lihogub.linkshortener.entity.Alias;

import java.util.Optional;

@Repository
public interface AliasRepository extends JpaRepository<Alias, Long> {
    Optional<Alias> findById(String id);
    boolean existsById(String id);
}

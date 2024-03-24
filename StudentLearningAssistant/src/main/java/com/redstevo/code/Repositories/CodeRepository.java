package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, String> {

    Optional<Code> findByUsername(String username);

    void deleteAllByUsername(String username);
}

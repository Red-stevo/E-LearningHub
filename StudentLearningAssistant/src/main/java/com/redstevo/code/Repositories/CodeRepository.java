package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface CodeRepository extends JpaRepository<Code, String> {

    Optional<Code> findByUsername(String username);

    void deleteByUsername(String username);
}

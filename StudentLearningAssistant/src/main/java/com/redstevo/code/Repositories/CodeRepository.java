package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface CodeRepository extends JpaRepository<Code, String> {

    Optional<Code> findByUsername(String username);

    void deleteByUsername(String username);
}

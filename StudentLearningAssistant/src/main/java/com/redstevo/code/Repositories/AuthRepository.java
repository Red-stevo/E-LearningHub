package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.AuthTable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<AuthTable, Long> {

    Optional<AuthTable> findByUsername(String username);

    Optional<Integer> countAllByUsername(String username);
}

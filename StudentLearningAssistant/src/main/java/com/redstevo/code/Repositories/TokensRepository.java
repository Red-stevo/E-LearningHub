package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.TokensTable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokensRepository extends CrudRepository<TokensTable, Long> {

    Optional<TokensTable> findByToken(String token);
}

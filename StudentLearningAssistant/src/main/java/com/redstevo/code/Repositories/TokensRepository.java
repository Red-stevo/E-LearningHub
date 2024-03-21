package com.redstevo.code.Repositories;

import com.redstevo.code.Tables.TokensTable;
import org.springframework.data.repository.CrudRepository;

public interface TokensRepository extends CrudRepository<TokensTable, Long> {
}

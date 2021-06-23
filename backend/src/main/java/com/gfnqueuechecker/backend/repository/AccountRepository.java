package com.gfnqueuechecker.backend.repository;

import com.gfnqueuechecker.backend.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

}

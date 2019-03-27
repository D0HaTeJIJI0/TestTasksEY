package com.travnich.account.repo;

import com.travnich.account.entity.Account;
import com.travnich.account.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

    Iterable<Account> findAccountsByDocument(Document document);

}

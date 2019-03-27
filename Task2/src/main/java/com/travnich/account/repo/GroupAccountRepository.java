package com.travnich.account.repo;

import com.travnich.account.entity.GroupAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupAccountRepository extends CrudRepository<GroupAccount, UUID> {
}

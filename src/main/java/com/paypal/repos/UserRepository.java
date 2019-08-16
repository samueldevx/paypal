package com.paypal.repos;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long>
{
    Optional<User> findByPaypalUserInfoEmail(String email);
}

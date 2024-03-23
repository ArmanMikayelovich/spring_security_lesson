package com.energizeglobal.itpm.repository;

import com.energizeglobal.itpm.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findAllByFirstNameContainsOrLastNameContainsOrEmailContains(String searchString,
                                                                                 String searchString2,
                                                                                 String searchString3);


}

package com.tech.kj.repository;

import com.tech.kj.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Query("select u from Users u inner join fetch u.emails e where e.email=:email ")
    Optional<Users> fetchedByEmail(@Param("email") String email);

    @Query("select u from Users u  where u.userName=:userName ")
    Optional<Users> loadByUserName(@Param("userName") String userName);
}

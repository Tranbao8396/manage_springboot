package com.stock.manage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.user_name = ?1")
    public Users getUserByUsername(String username);

    @Modifying
    @Query("UPDATE Users u SET u.user_pass = :password WHERE u.id = :id")
    public void updatePassword(@Param("id") Long id, @Param("password") String password);
}

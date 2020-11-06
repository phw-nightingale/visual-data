package com.deepazure.visualdata.repository;

import com.deepazure.visualdata.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByAccessToken(String accessToken);

}

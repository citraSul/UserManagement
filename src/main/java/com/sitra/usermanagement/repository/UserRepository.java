package com.sitra.usermanagement.repository;
import com.sitra.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findBySupervisorUserId(String supervisorUserId);

    Optional<User> findByUserId(String userId);
}

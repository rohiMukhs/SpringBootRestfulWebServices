package net.javaguides.springbootRestfulWebServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springbootRestfulWebServices.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

package Rollbase.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Rollbase.Entity.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser , Long>{
	
	Optional<MyUser> findByUsername(String username);

}

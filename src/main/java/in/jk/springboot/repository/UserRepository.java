package in.jk.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.jk.springboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	@Query("From UserEntity user where user.userName=:userName")
	public List<UserEntity> findUserByUserName(@Param("userName") String userName); 

}

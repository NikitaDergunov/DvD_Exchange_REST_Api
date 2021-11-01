package com.ndergunov.dvdexchange.model.jpa;

import com.ndergunov.dvdexchange.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
User findByLogin(String login);
User findByLoginAndPassword(String login,String password);
boolean existsByLoginAndPassword(String login,String password);
}

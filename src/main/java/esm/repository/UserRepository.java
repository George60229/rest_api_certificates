package esm.repository;


import esm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into user_role values (?1,?1)")
    void addRole(int userId,int roleId);
    User findByLogin(String login);


    @Query("select u from Order o full join User u on o.id = u.userId group by u.userId order by sum(o.price)")
    List<User> findExpensiveOrder();



}

package esm.repository;


import esm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);


    @Query("select u from Order o full join User u on o.id = u.userId group by u.userId order by sum(o.price)")
    List<User> findExpensiveOrder();



}

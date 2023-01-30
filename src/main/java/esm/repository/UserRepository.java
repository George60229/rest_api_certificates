package esm.repository;



import esm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.math.BigDecimal;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByOrders_PriceGreaterThanEqual(BigDecimal price);
    User findByOrdersPrice(BigDecimal price);

    @Query("select sum(o.price) from Order o full join User u on o.id = u.userId group by u.userId")
    List<BigDecimal> findExpensiveOrder();


}

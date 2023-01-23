package esm.service;



import esm.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();
}

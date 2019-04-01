import dao.UserRepository;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserDaoTest {

    private static UserRepository userRepository;

    @BeforeAll
    public static void initTest(){
        userRepository = new UserRepository("jdbc:h2:mem:in_memory_mod");
    }

    @Test
    void getByIdTest(){
        List<String> skills = Arrays.asList("dmg", "sap");
        User user = new User(1,"Johnny", 25, false, skills);
        userRepository.add(user);
        Optional<User> userByIDOptional = userRepository.getById(1);

        assert userByIDOptional.isPresent() && userByIDOptional.get().getName().equals(user.getName());
    }

    @Test
    void deleteTest(){
        List<String> skills = Arrays.asList("dmg", "sap");
        User user = new User(1,"Johnny", 25, false, skills);
        userRepository.add(user);
        userRepository.delete(1);

        assert userRepository.getById(1).equals(Optional.empty());
    }
}

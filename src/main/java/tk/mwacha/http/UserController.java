package tk.mwacha.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mwacha.http.dto.User;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

//    @GetMapping("")
//    public List<User> getAllUsers() {
////        LOG.info("Fetching all the users");
//        return Arrays.asList(
//                new User(UUID.randomUUID().toString(), "User1", "user1@mail.com"),
//                new User(UUID.randomUUID().toString(), "User2", "user2@mail.com"),
//                new User(UUID.randomUUID().toString(), "User3", "user3@mail.com"));
//    }

}

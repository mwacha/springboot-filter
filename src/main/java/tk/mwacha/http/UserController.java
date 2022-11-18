package tk.mwacha.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tk.mwacha.http.dto.UserDTO;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @GetMapping
    public List<UserDTO> getAllUsers() {
        log.info("Fetching all the users");
        return Arrays.asList(
                UserDTO.builder().id(UUID.randomUUID().toString()).name("User1").email("user1@mail.com").build(),
                UserDTO.builder().id(UUID.randomUUID().toString()).name("User2").email("user2@mail.com").build(),
                UserDTO.builder().id(UUID.randomUUID().toString()).name("User3").email("user3@mail.com").build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody List<UserDTO> users) {
        log.info("save users: {}", users.stream().count());

    }

}

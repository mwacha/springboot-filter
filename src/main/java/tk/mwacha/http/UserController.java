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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody List<UserDTO> users) {
        log.info("save users: {}", users.stream().count());

    }

}

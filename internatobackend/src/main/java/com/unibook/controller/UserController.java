import com.unibook.entity.User;
import com.unibook.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping
    public User create(@RequestBody User u) {
        return service.save(u);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return service.getById(id);
    }
}
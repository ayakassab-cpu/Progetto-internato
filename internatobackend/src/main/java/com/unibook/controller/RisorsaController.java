import com.unibook.entity.Risorsa;
import com.unibook.service.RisorsaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risorse")
@RequiredArgsConstructor
public class RisorsaController {

    private final RisorsaService service;


    @GetMapping
    public List<Risorsa> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Risorsa create(@RequestBody Risorsa r) {
        return service.save(r);
    }

    @GetMapping("/{id}")
    public Risorsa get(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

import com.unibook.entity.BloccaManutenzione;
import com.unibook.service.BloccaManutenzioneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocchi")
@RequiredArgsConstructor
public class BloccaManutenzioneController {

    private final BloccaManutenzioneService service;

    @PostMapping("/{risorsaId}")
    public BloccaManutenzione create(
            @PathVariable Long risorsaId,
            @RequestBody BloccaManutenzione blocco
    ) {
        return service.create(risorsaId, blocco);
    }

    @GetMapping
    public List<BloccaManutenzione> getAll() {
        return service.getAll();
    }
}
package mi_playlist.controller;


import mi_playlist.model.VideoModel;
import mi_playlist.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VideoController {
    private final VideoService service;
    public VideoController(VideoService service) { this.service = service; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("videos", service.listar());
        return "index";
    }

    @RestController
    @RequestMapping("/api/videos")
    public static class VideoApiController {
        private final VideoService service;
        public VideoApiController(VideoService service) { this.service = service; }

        @GetMapping
        public List<VideoModel> listar() { return service.listar(); }

        @PostMapping
        public VideoModel agregar(@RequestBody VideoModel v) { return service.agregarConEstadisticas(v); }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }

        @PostMapping("/{id}/like")
        public VideoModel like(@PathVariable Long id) { return service.sumarLike(id); }

        @PostMapping("/{id}/favorito")
        public VideoModel favorito(@PathVariable Long id) { return service.toggleFavorito(id); }
    }
}


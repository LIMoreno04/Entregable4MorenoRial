package mi_playlist.controller;

import mi_playlist.model.UserModel;
import mi_playlist.model.VideoModel;
import mi_playlist.service.UserService;
import mi_playlist.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final VideoService videoService;

    public UserController(UserService userService, VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userService.obtenerTodosLosUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuario/{id}")
    public String playlistUsuario(@PathVariable Long id, Model model) {
        UserModel usuario = userService.obtenerUsuarioPorId(id);
        if (usuario == null) {
            return "redirect:/usuarios";
        }
        List<VideoModel> videosUsuario = videoService.listarPorUsuario(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("videos", videosUsuario);
        return "playlist-usuario";
    }

    @PostMapping("/usuario/crear")
    public String crearUsuario(@RequestParam String nombre) {
        userService.crearUsuario(nombre);
        return "redirect:/usuarios";
    }

    @PostMapping("/usuario/{id}/eliminar")
    public String eliminarUsuario(@PathVariable Long id) {
        userService.eliminarUsuario(id);
        return "redirect:/usuarios";
    }

    @RestController
    @RequestMapping("/api/usuarios")
    public static class UserApiController {

        private final UserService userService;
        private final VideoService videoService;

        public UserApiController(UserService userService, VideoService videoService) {
            this.userService = userService;
            this.videoService = videoService;
        }

        @GetMapping
        public List<UserModel> listarUsuarios() {
            return userService.obtenerTodosLosUsuarios();
        }

        @GetMapping("/{id}")
        public ResponseEntity<UserModel> obtenerUsuario(@PathVariable Long id) {
            UserModel usuario = userService.obtenerUsuarioPorId(id);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping
        public UserModel crearUsuario(@RequestBody UserModel usuario) {
            return userService.guardarUsuario(usuario);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
            userService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}/videos")
        public List<VideoModel> obtenerVideosUsuario(@PathVariable Long id) {
            return videoService.listarPorUsuario(id);
        }

        @PostMapping("/{usuarioId}/videos")
        public VideoModel agregarVideoAUsuario(@PathVariable Long usuarioId, @RequestBody VideoModel video) {
            UserModel usuario = userService.obtenerUsuarioPorId(usuarioId);
            if (usuario != null) {
                video.setUsuario(usuario);
                return videoService.agregarConEstadisticas(video);
            }
            return null;
        }
    }
}

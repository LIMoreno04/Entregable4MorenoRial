package mi_playlist.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String avatar; // URL o path del avatar

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoModel> videos;

    // Constructores
    public UserModel() {}
    public UserModel(String nombre) {
        this.nombre = nombre;
        this.avatar = "/images/default-avatar.png"; // Avatar por defecto
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }
    public List<VideoModel> getVideos() { return videos; }
    public void setVideos(List<VideoModel> videos) { this.videos = videos; }
}

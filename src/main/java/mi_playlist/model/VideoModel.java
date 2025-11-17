package mi_playlist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class VideoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String url;
    private int likes;
    private boolean favorito;
    private Long originalLikes;
    private Long originalViews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private UserModel usuario;

    // Constructores
    public VideoModel() {}
    public VideoModel(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
        this.likes = 0;
        this.favorito = false;
        this.originalLikes = 0L;
        this.originalViews = 0L;
    }

    // getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public boolean isFavorito() { return favorito; }
    public void setFavorito(boolean favorito) { this.favorito = favorito; }
    public Long getOriginalLikes() { return originalLikes; }
    public void setOriginalLikes(Long originalLikes) { this.originalLikes = originalLikes; }
    public Long getOriginalViews() { return originalViews; }
    public void setOriginalViews(Long originalViews) { this.originalViews = originalViews; }
    public UserModel getUsuario() { return usuario; }
    public void setUsuario(UserModel usuario) { this.usuario = usuario; }
}


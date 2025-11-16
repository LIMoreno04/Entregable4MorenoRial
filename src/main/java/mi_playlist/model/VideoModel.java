package mi_playlist.model;

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

    // Constructores
    public VideoModel() {}
    public VideoModel(String nombre, String url) {
        this.nombre = nombre;
        this.url = url;
        this.likes = 0;
        this.favorito = false;
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
}


package mi_playlist.service;


import mi_playlist.model.VideoModel;
import mi_playlist.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepository repo;

    public VideoService(VideoRepository repo) { this.repo = repo; }

    public List<VideoModel> listar() { return repo.findAll(); }

    public VideoModel agregar(VideoModel v) { return repo.save(v); }

    public void eliminar(Long id) { repo.deleteById(id); }

    public Optional<VideoModel> getPorId(Long id) { return repo.findById(id); }

    public VideoModel sumarLike(Long id) {
        VideoModel v = repo.findById(id).orElseThrow();
        v.setLikes(v.getLikes() + 1);
        return repo.save(v);
    }

    public VideoModel toggleFavorito(Long id) {
        VideoModel v = repo.findById(id).orElseThrow();
        v.setFavorito(!v.isFavorito());
        return repo.save(v);
    }
}

package mi_playlist;

import mi_playlist.model.VideoModel;
import mi_playlist.repository.VideoRepository;
import mi_playlist.service.VideoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoServiceTests {

    @Test
    void sumarLike_incrementsLikes() {
        VideoRepository repo = mock(VideoRepository.class);
        VideoModel v = new VideoModel("nombre","https://youtu.be/example");
        v.setId(1L);
        v.setLikes(0);
        when(repo.findById(1L)).thenReturn(Optional.of(v));
        when(repo.save(any())).thenAnswer(i -> i.getArgument(0));

        VideoService service = new VideoService(repo);
        VideoModel res = service.sumarLike(1L);
        assertEquals(1, res.getLikes());
        verify(repo, times(1)).save(any());
    }
}

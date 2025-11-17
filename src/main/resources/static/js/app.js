// API base URL
const API_BASE = '/api/videos';

// Load videos on page load
document.addEventListener('DOMContentLoaded', loadVideos);

// Handle form submission
document.getElementById('addVideoForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const name = document.getElementById('videoName').value;
    const url = document.getElementById('videoUrl').value;

    addVideo(name, url);
});

// Load all videos
async function loadVideos() {
    try {
        const response = await fetch(API_BASE);
        const videos = await response.json();
        displayVideos(videos);
    } catch (error) {
        console.error('Error loading videos:', error);
    }
}

// Add new video
async function addVideo(name, url) {
    try {
        const response = await fetch(API_BASE, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre: name, url: url }),
        });

        if (response.ok) {
            document.getElementById('videoName').value = '';
            document.getElementById('videoUrl').value = '';
            // Reload page to show server-side rendered content
            window.location.reload();
        }
    } catch (error) {
        console.error('Error adding video:', error);
    }
}

// Delete video
async function deleteVideo(button) {
    const id = button.getAttribute('data-id');
    if (confirm('¿Estás seguro de que quieres eliminar este video?')) {
        try {
            const response = await fetch(`${API_BASE}/${id}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                // Reload page to show server-side rendered content
                window.location.reload();
            }
        } catch (error) {
            console.error('Error deleting video:', error);
        }
    }
}

// Like video
async function likeVideo(id) {
    try {
        const response = await fetch(`${API_BASE}/${id}/like`, {
            method: 'POST',
        });

        if (response.ok) {
            // Reload page to show server-side rendered content
            window.location.reload();
        }
    } catch (error) {
        console.error('Error liking video:', error);
    }
}

// Toggle favorite
async function toggleFavorite(button) {
    const id = button.getAttribute('data-id');
    try {
        const response = await fetch(`${API_BASE}/${id}/favorito`, {
            method: 'POST',
        });

        if (response.ok) {
            // Reload page to show server-side rendered content
            window.location.reload();
        }
    } catch (error) {
        console.error('Error toggling favorite:', error);
    }
}

// Display videos in the UI (for dynamic updates if needed)
function displayVideos(videos) {
    // Since we're using server-side rendering, this function is kept for potential future use
    // Currently, the page reloads after operations to show updated server-rendered content
}

// Show video in modal
function showVideo(button) {
    const title = button.getAttribute('data-nombre');
    const url = button.getAttribute('data-url');

    document.getElementById('videoModalTitle').textContent = title;

    const embedContainer = document.getElementById('videoEmbed');
    embedContainer.innerHTML = getVideoEmbed(url);

    const modal = new bootstrap.Modal(document.getElementById('videoModal'));
    modal.show();
}

// Get embed code for different video platforms
function getVideoEmbed(url) {
    if (url.includes('youtube.com') || url.includes('youtu.be')) {
        const videoId = getYouTubeVideoId(url);
        return `<div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${videoId}" allowfullscreen></iframe>
                </div>`;
    } else if (url.includes('vimeo.com')) {
        const videoId = getVimeoVideoId(url);
        return `<div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://player.vimeo.com/video/${videoId}" allowfullscreen></iframe>
                </div>`;
    } else {
        return `<p>URL no soportada para embed. <a href="${url}" target="_blank">Abrir en nueva pestaña</a></p>`;
    }
}

// Extract YouTube video ID
function getYouTubeVideoId(url) {
    const regExp = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=)([^#&?]*).*/;
    const match = url.match(regExp);
    return (match && match[2].length == 11) ? match[2] : null;
}

// Extract Vimeo video ID
function getVimeoVideoId(url) {
    const regExp = /vimeo\.com\/(\d+)/;
    const match = url.match(regExp);
    return match ? match[1] : null;
}

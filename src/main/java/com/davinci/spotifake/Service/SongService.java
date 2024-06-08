package com.davinci.spotifake.Service;

public class SongService {
    private SongRepository repository;

    public SongService(SongRepository repository) {
        this.repository = repository;
    }

    public void createSong(int id, String name, Genre genre, String lyrics) {
        Song song = new Song(id, name, genre, lyrics);
        repository.create(song);
    }

    public void updateSong(Song song) {
        repository.update(song);
    }

    public Song findSongById(int id) {
        return repository.findById(id);
    }

    public List<Song> findSongsByName(String name) {
        return repository.findByName(name);
    }

    public List<Song> findSongsByLyrics(String lyrics) {
        return repository.findByLyrics(lyrics);
    }
}
package org.lessons.java.spring_hitsmoviessongs.controllers;

import java.util.Arrays;
import java.util.List;

import org.lessons.java.spring_hitsmoviessongs.classes.Movie;
import org.lessons.java.spring_hitsmoviessongs.classes.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller   //add this!
public class MainController {
    
    private List<Movie> getBestMovies(){
        return Arrays.asList(
                new Movie(1, "F&F"),
                new Movie(2, "Interstellar"),
                new Movie(3, "Onion Field")
        );
    }
    private List<Song> getBestSongs() {
        return Arrays.asList(
                new Song(1, "Shizen"),
                new Song(2, "Hotel Lux"),
                new Song(3, "Imagine")
        );
    }

    @GetMapping("/")
    public String indexPage(@RequestParam(name="username", required=false, defaultValue="Nevil") String username, 
    Model model){
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/movies")
    public String moviesPage(
    Model model){
        List<Movie> movies = getBestMovies();
        List<String> movieTitles = movies.stream().map(Movie::getTitle).toList();
        String bestmovies = String.join(", ", movieTitles);
        model.addAttribute("bestmovies", bestmovies);
        return "movies";
    }

    @GetMapping("/songs")
    public String songsPage(
    Model model){
        List<Song> songs = getBestSongs();
        List<String> songsTitles = songs.stream().map(Song::getTitle).toList();
        String bestsongs =String.join(", ",songsTitles);
        model.addAttribute("bestsongs", bestsongs);
        return "songs";
    }
    
}

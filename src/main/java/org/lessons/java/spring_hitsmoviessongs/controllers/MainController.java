package org.lessons.java.spring_hitsmoviessongs.controllers;

import java.util.Arrays;
import java.util.List;

import org.lessons.java.spring_hitsmoviessongs.classes.Movie;
import org.lessons.java.spring_hitsmoviessongs.classes.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.SessionAttributes;


@Controller   //add this!
@SessionAttributes("username")  // Conserva sempre 'username' sessione attiva!
public class MainController {
    
    private List<Movie> getBestMovies(){
        return Arrays.asList(
                new Movie(1, "Fast & Furious", "f&f.jpg"),
                new Movie(2, "Atlas", "atlas.jpg"),
                new Movie(3, "Onion Field","onion-field.jpg")
        );
    }
    private List<Song> getBestSongs() {
        return Arrays.asList(
                new Song(1, "Move","Camila Cabello", "https://www.youtube.com/embed/si6Ox8IuZeU?si=qT-VW88siBuPpO-h"),
                new Song(2, "Shots in the dark","Iann Dior","https://www.youtube.com/embed/4ZMq4-T0DaQ?si=zfEKEThBnv9ynSWx"),
                new Song(3, "I had some help","Post Malone","https://www.youtube.com/embed/4QIZE708gJ4?si=-YQUEDW4aPUo-bIE")
        );
    }

    @ModelAttribute("username")  
    public String getUsername(){    //se spring non trova 'username' nella sessione, allora chiama questo
        return "Anonymous";
    }
    
    @PostMapping("/set-username")  //run after form POST submit th:action="@{/set-username}"
    public String setUsername(@RequestParam("username") String username,
    Model model,
    HttpServletRequest request){
        model.addAttribute("username", username);
        String referer = request.getHeader("Referer"); //get string url now
        return "redirect:" + (referer != null ? referer : "/"); //redirect to home no url params
    }


    @GetMapping("/")
    public String indexPage(@RequestParam(name="username",required=false) String username,
    Model model){
        if (username!= null && !username.isEmpty()){
            model.addAttribute("username", username);  // Aggiorna l'username nella sessione
            return "redirect:/"; // Redirect per rimuovere il parametro dalla URL
        }
        return "index";
    }


    //MOVIES
    @GetMapping({"/movies" ,"/movies/"})  //without '/movies/', if you navigate to localhost/movies/ you will get errors!
    public String moviesPage(
    Model model){
        List<Movie> movies = getBestMovies();
        List<String> movieTitles = movies.stream().map(Movie::getTitle).toList();
        String bestmovies = String.join(", ", movieTitles);
        // List<Movie> moviesWStringId = movies.stream()  
        //     .map(movie->new Movie(Integer.parseInt(String.valueOf(movie.getId())), movie.getTitle()))
        //     .toList();   //convert int id in str, but target url needs int
        model.addAttribute("bestmovies", movies); 
        return "movies";
    }

    @GetMapping("/movies/{id}")  //if use a path with {}, you must use @PathVariable!
    public String moviesPage(@PathVariable int id,  //get the exact param from the path
    Model model){
        List<Movie> movies = getBestMovies(); 
        Movie targetmovie = movies.stream().filter(movie->movie.getId() == id).findFirst().orElse(null);
        model.addAttribute("targetmovie", targetmovie);

        return "movie-detail";
    }


    //SONGS
    @GetMapping({"/songs","/songs/"})
    public String songsPage(
    Model model){
        List<Song> songs = getBestSongs();
        List<String> songsTitles = songs.stream().map(Song::getTitle).toList();
        String bestsongs =String.join(", ",songsTitles);
        model.addAttribute("bestsongs", songs);
        return "songs";
    }
    @GetMapping("/songs/{id}")
    public String songsPage(@PathVariable int id,
    Model model){
        List<Song> songs = getBestSongs();
        Song targetsong = songs.stream().filter(song->song.getId() == id).findFirst().orElse(null);
        model.addAttribute("targetsong",targetsong);
        return "song-detail";
    }

}

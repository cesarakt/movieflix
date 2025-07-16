package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/movieflix/movie")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> findAll() {
        List<MovieResponse> movieResponses = movieService.findAll()
                .stream()
                .map(movie -> MovieMapper.toMovieResponse(movie))
                .toList();
        return ResponseEntity.ok(movieResponses);
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        MovieResponse movieResponse = MovieMapper.toMovieResponse(savedMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }
}

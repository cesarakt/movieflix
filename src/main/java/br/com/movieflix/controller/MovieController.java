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
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> findById(@PathVariable Long id) {
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.save(MovieMapper.toMovie(movieRequest));
        MovieResponse movieResponse = MovieMapper.toMovieResponse(savedMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest movieRequest) {
        Movie movie = MovieMapper.toMovie(movieRequest);
        return movieService.update(id, movie)
                .map(m -> ResponseEntity.ok(MovieMapper.toMovieResponse(m)))
                .orElse(ResponseEntity.notFound().build());
    }

}

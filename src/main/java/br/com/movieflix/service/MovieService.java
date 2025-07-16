package br.com.movieflix.service;

import br.com.movieflix.entity.Movie;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository repository;

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById (Long id) {
        repository.deleteById(id);
    }
}

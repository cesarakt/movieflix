package br.com.movieflix.service;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public List<Movie> findAll() {
        return repository.findAll();
    }

    public List<Movie> findByCategory(Long categoryId) {
        return repository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public Movie save(Movie movie) {
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Category> findCategories (List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<Category>();
        categories
                .forEach(category -> categoryService.findById(category.getId())
                        .ifPresent(c -> categoriesFound.add(c)));
        return categoriesFound;
    }

    public List<Streaming> findStreamings (List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<Streaming>();
        streamings
                .forEach(streaming -> streamingService.findById(streaming.getId())
                        .ifPresent(s -> streamingsFound.add(s)));
        return streamingsFound;
    }


    public Optional<Movie> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Movie> update (Long id, Movie movieUpdate) {
        Optional<Movie> movieById = repository.findById(id);
        if (movieById.isPresent()) {
            List<Category> categories = this.findCategories(movieUpdate.getCategories());
            List<Streaming> streamings = this.findStreamings(movieUpdate.getStreamings());

            Movie movie = movieById.get();
            movie.setTitle(movieUpdate.getTitle());
            movie.setDescription(movieUpdate.getDescription());
            movie.setRating(movieUpdate.getRating());
            movie.setReleaseDate(movieUpdate.getReleaseDate());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            repository.save(movie);
            return Optional.of(movie);
        }

        return Optional.empty();
    }

    public void deleteById (Long id) {
        repository.deleteById(id);
    }
}
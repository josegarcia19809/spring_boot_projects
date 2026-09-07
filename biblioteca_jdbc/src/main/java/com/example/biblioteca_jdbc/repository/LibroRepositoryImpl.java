package com.example.biblioteca_jdbc.repository;

import com.example.biblioteca_jdbc.model.Libro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibroRepositoryImpl implements ILibroRepository {

    private final JdbcTemplate jdbcTemplate;

    public LibroRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Libro> libroRowMapper = (rs, rowNum) -> {

        Libro libro = new Libro();

        libro.setId(rs.getLong("id"));
        libro.setTitulo(rs.getString("titulo"));
        libro.setIsbn(rs.getString("isbn"));

        return libro;
    };

    @Override
    public List<Libro> findByTitulo(String titulo) {

        String sql = """
                SELECT id, titulo, isbn
                FROM libro
                WHERE titulo LIKE ?
                """;

        return jdbcTemplate.query(
                sql,
                libroRowMapper,
                "%" + titulo + "%"
        );
    }
}

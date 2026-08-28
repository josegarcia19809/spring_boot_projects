package com.portafolio.mi_portafolio_backend.repository;

import com.portafolio.mi_portafolio_backend.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositorioImpl
        implements IPersonalInfoRepositorio {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = ((rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setId(rs.getLong("id"));
        personalInfo.setNombre(rs.getString("nombre"));
        personalInfo.setApellido(rs.getString("apellido"));
        personalInfo.setCorreo(rs.getString("correo"));
        personalInfo.setTelefono(rs.getString("telefono"));
        personalInfo.setTitulo(rs.getString("titulo"));
        personalInfo.setDescripcionPerfil(
                rs.getString("descripcion_perfil")
        );
        personalInfo.setUrlImagenPerfil(
                rs.getString("url_imagen_perfil")
        );
        personalInfo.setAniosExperiencia(
                rs.getObject("anios_experiencia", Integer.class)
        );
        personalInfo.setUrlLinkedin(
                rs.getString("url_linkedin")
        );
        personalInfo.setUrlGithub(
                rs.getString("url_github")
        );

        return personalInfo;
    });

    @Override
    public PersonalInfo guardar(PersonalInfo informacionPersonal) {

        if (informacionPersonal.getId() == null) {

            String sql = "INSERT INTO personal_info " +
                    "(nombre, apellido, titulo, descripcion_perfil, " +
                    "url_imagen_perfil, anios_experiencia, correo, " +
                    "telefono, url_linkedin, url_github) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {

                PreparedStatement ps = connection.prepareStatement(
                        sql,
                        new String[]{"id"}
                );

                ps.setString(1, informacionPersonal.getNombre());
                ps.setString(2, informacionPersonal.getApellido());
                ps.setString(3, informacionPersonal.getTitulo());
                ps.setString(4, informacionPersonal.getDescripcionPerfil());
                ps.setString(5, informacionPersonal.getUrlImagenPerfil());

                if (informacionPersonal.getAniosExperiencia() != null) {
                    ps.setInt(6, informacionPersonal.getAniosExperiencia());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, informacionPersonal.getCorreo());
                ps.setString(8, informacionPersonal.getTelefono());
                ps.setString(9, informacionPersonal.getUrlLinkedin());
                ps.setString(10, informacionPersonal.getUrlGithub());

                return ps;

            }, keyHolder);

            informacionPersonal.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        } else {

            String sql = "UPDATE personal_info SET " +
                    "nombre=?, " +
                    "apellido=?, " +
                    "titulo=?, " +
                    "descripcion_perfil=?, " +
                    "url_imagen_perfil=?, " +
                    "anios_experiencia=?, " +
                    "correo=?, " +
                    "telefono=?, " +
                    "url_linkedin=?, " +
                    "url_github=? " +
                    "WHERE id=?";

            jdbcTemplate.update(
                    sql,
                    informacionPersonal.getNombre(),
                    informacionPersonal.getApellido(),
                    informacionPersonal.getTitulo(),
                    informacionPersonal.getDescripcionPerfil(),
                    informacionPersonal.getUrlImagenPerfil(),
                    informacionPersonal.getAniosExperiencia(),
                    informacionPersonal.getCorreo(),
                    informacionPersonal.getTelefono(),
                    informacionPersonal.getUrlLinkedin(),
                    informacionPersonal.getUrlGithub(),
                    informacionPersonal.getId()
            );
        }

        return informacionPersonal;
    }

    @Override
    public Optional<PersonalInfo> buscarPorId(Long id) {

        String sql = "SELECT * FROM personal_info WHERE id=?";

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> buscarTodos() {
        String sql = "SELECT * FROM personal_info";

        return jdbcTemplate.query(
                sql,
                personalInfoRowMapper
        );
    }

    @Override
    public void eliminarPorId(Long id) {
        String sql = "DELETE FROM personal_info WHERE id=?";

        jdbcTemplate.update(sql, id);
    }
}
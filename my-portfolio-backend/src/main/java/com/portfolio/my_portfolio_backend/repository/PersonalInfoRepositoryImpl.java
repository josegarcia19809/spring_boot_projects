package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.models.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PersonalInfo> personalInfoRowMapper = ((rs, rowNum) -> {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setId(rs.getLong("id"));
        personalInfo.setFirstName(rs.getString("first_name"));
        personalInfo.setLastName(rs.getString("last_name"));
        personalInfo.setEmail(rs.getString("email"));
        personalInfo.setPhone(rs.getString("phone"));
        personalInfo.setTitle(rs.getString("title"));
        personalInfo.setProfileDescription(rs.getString("profile_description"));
        personalInfo.setProfileImageUrl(rs.getString("profile_image_url"));
        personalInfo.setYearsOfExperience(rs.getObject("years_of_experience", Integer.class));
        personalInfo.setLinkedinUrl(rs.getString("linkedin_url"));
        personalInfo.setGithubUrl(rs.getString("github_url"));
        return personalInfo;
    });

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return null;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";

        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {

    }
}

package com.openclassrooms.mddapi.Repository;

import com.openclassrooms.mddapi.Models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThemeRepo extends JpaRepository<Theme, Long> {
    public Optional<Theme> findByName(String title);

}
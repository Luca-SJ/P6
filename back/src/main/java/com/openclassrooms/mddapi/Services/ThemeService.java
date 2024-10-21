package com.openclassrooms.mddapi.Services;

import com.openclassrooms.mddapi.Exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.Models.Subscription;
import com.openclassrooms.mddapi.Models.Theme;
import com.openclassrooms.mddapi.Models.User;
import com.openclassrooms.mddapi.Repository.SubscriptionRepo;
import com.openclassrooms.mddapi.Repository.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepo themeRepository;
    private SubscriptionRepo subscriptionRepo;

    public List<Theme> findAll() {
        return themeRepository.findAll();
    }

    public Theme findByID(Long ThemeID) throws ResourceNotFoundException {
        return themeRepository.findById(ThemeID)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));
    }

    public Theme findByName(String name) throws ResourceNotFoundException {
        return themeRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Theme inexistant"));
    }

    public void deleteByID(Long ThemeID) throws ResourceNotFoundException {
        Theme theme = themeRepository.findById(ThemeID)
                .orElseThrow(()->new ResourceNotFoundException("Theme avec ID : " + ThemeID + " inexistant"));
        themeRepository.delete(theme);
    }

    public Theme updateThemeByID(Long ThemeID, Theme themeDetails) throws ResourceNotFoundException {
        Theme theme = themeRepository.findById(ThemeID)
                .orElseThrow(()->new ResourceNotFoundException("Theme avec ID : "+ ThemeID + " inexistant"));

        theme.setName(themeDetails.getName());
        theme.setDescription(themeDetails.getDescription());
        theme.setCreated_at(themeDetails.getCreated_at());
        theme.setUpdated_at(themeDetails.getUpdated_at());

        return themeRepository.save(theme);
    }

    public Theme createTheme(Theme theme) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        theme.setCreated_at(sqlDate);
        theme.setDescription(theme.getDescription());
        theme.setName(theme.getName());

        return themeRepository.save(theme);
    }

    public Subscription subscribe(long userId, long themeId) {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        Subscription subscription = new Subscription();
        subscription.setIdUser(userId);
        subscription.setIdTheme(themeId);
        subscription.setCreated_at(sqlDate);


        return subscriptionRepo.save(subscription);
    }
}

package com.ecse428.project.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ecse428.project.model.Alcohol;
import com.ecse428.project.model.Modifier;
import com.ecse428.project.model.User;
import com.ecse428.project.repository.AlcoholRepository;
import com.ecse428.project.repository.ModifierRepository;
import com.ecse428.project.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final ModifierRepository modifierRepository;
    private final AlcoholRepository alcoholRepository;

    @Autowired
    public IUserService(UserRepository userRepository, ModifierRepository modifierRepository, AlcoholRepository alcoholRepository) {
        this.userRepository = userRepository;
        this.modifierRepository = modifierRepository;
        this.alcoholRepository = alcoholRepository;
    }

    @Override
    public Set<Modifier> getModifiersInInventory(long userId) {
        // TODO
        // Find user
        // Return modifiersInInventory
        return null;
    }

    @Override
    public void putModifierInInventory(long userId, String modifierName) {
        // Find user in database
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found with id " + userId + ".");
        }
        // Find modifier in database
        Optional<Modifier> modifier = modifierRepository.findByName(modifierName);
        if (!modifier.isPresent()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Modifier not found with name " + modifierName + ".");
        }

        // Add modifier to user's modifiersInInventory
        user.get().getModifiersInInventory().add(modifier.get());
        
        // Save user
        userRepository.save(user.get());
    }

    @Override
    public Set<Alcohol> getAlcoholInInventory(long userId) {
        // TODO
        // Find user
        // Return modifiersInInventory
        return null;
    }

    @Override
    public void putAlcoholInInventory(long userId, String alcoholName) {
        // TODO
        // Find user in database
        // Find alcohol in database
        // Error if either doesn't exist
        // Else add alcohol to user's alcoholInInventory
        // Save user
    }
}

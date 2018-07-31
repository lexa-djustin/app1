package com.app1.example.Service;

import com.app1.example.Entity.Profile;
import com.app1.example.Entity.Row;
import com.app1.example.Dao.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements ProfileServiceInterface {

    @Autowired(required = true)
    private ProfileRepository profileRepository;

    public void save(Profile profile) {
        for(Row row : profile.getRows()){
            row.setProfile(profile);
        }

        profileRepository.saveAndFlush(profile);
    }

    public Profile get(long id) {
        return this.profileRepository.findById(id);
    }

    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public void delete(Profile profile) {
        this.profileRepository.delete(profile);
    }
}

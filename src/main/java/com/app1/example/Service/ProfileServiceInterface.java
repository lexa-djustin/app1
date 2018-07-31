package com.app1.example.Service;
import com.app1.example.Entity.Profile;

import java.util.List;

public interface ProfileServiceInterface {

    public void save(Profile profile);

    public Profile get(long id);

    public List<Profile> getAll();

    public void delete(Profile profile);
}

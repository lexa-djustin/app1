package com.app1.example.Dao;

import com.app1.example.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

    Profile findById(Long var1);

}

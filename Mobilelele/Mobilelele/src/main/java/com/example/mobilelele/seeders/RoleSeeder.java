package com.example.mobilelele.seeders;

import com.example.mobilelele.Models.Entity.Enums.UserRoleEnum;
import com.example.mobilelele.Models.Entity.RoleEntity;
import com.example.mobilelele.Repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository repository;

    public RoleSeeder(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            List<RoleEntity> roles = Arrays.stream(UserRoleEnum.values()).
                    map(RoleEntity::new).
                    toList();
            this.repository.saveAll(roles);
        }
    }
}

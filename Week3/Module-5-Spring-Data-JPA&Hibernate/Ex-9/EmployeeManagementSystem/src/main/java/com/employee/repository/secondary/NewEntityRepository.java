package com.employee.repository.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.secondary.NewEntity;

@Repository
public interface NewEntityRepository extends JpaRepository<NewEntity, Long> {
}
    
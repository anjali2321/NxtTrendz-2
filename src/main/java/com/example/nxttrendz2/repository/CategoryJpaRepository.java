
package com.example.nxttrendz2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nxttrendz2.model.Category;

@Repository

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {

}

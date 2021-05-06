package com.codeup.lunablog.repositories;

import com.codeup.lunablog.models.AdCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdCategoryRepo extends JpaRepository<AdCategory, Long> {
}

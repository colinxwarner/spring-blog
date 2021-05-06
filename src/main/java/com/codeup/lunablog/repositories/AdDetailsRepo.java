package com.codeup.lunablog.repositories;

import com.codeup.lunablog.models.AdDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdDetailsRepo extends JpaRepository<AdDetails, Long> {
}

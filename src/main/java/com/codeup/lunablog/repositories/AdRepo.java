package com.codeup.lunablog.repositories;

import com.codeup.lunablog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepo extends JpaRepository<Ad, Long> {
}

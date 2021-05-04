package com.codeup.lunablog.repositories;

import com.codeup.lunablog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepo extends JpaRepository<Ad, Long> {

}

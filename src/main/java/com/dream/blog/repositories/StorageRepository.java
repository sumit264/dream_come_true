package com.dream.blog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dream.blog.entities.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {


    Optional<ImageData> findByName(String fileName);
}

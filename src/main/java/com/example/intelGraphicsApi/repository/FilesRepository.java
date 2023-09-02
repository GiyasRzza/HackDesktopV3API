package com.example.intelGraphicsApi.repository;

import com.example.intelGraphicsApi.entity.UpFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<UpFiles,Long> {
    UpFiles findByFileName(String name);
   List<UpFiles> findByCompany_Name(String name);
   String deleteByFileName(String name);
}

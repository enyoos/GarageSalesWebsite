package com.api.grg.envy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.grg.envy.post.Post;

import jakarta.transaction.Transactional;

public interface RepositoryPost extends JpaRepository<Post, Long>{

    List<Post> findByVendorId(Long id);
    
    @Transactional
    void deleteByVendorId(Long id );

}

package com.example.demo.Rrepository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Post;

public interface PostRep extends CrudRepository<Post, Long> {

}

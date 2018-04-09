package com.github.cvazer.tryout.playgendary.services;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Service {
    <T, K> void exceptionIfNonExisting(K id, String message, JpaRepository<T, K> repository) throws RuntimeException;
}

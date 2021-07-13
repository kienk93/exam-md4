package com.codegym.exammd4.service;

import java.util.Optional;

public interface IGeneralService<E> {
    Iterable<E> findAll();

    E save(E e);

    Optional<E> findById(Long id);

    void deleteById(Long id);


}

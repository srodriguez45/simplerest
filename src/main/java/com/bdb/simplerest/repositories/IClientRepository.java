package com.bdb.simplerest.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bdb.simplerest.models.Client;


@Repository
public interface IClientRepository extends CrudRepository<Client, Integer> {

}

package com.zealous.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zealous.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}

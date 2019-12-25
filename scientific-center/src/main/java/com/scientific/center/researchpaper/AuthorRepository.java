package com.scientific.center.researchpaper;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, BigInteger> {

}

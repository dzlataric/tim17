package com.scientific.center.magazine;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepository extends JpaRepository<MagazineEntity, BigInteger> {

}

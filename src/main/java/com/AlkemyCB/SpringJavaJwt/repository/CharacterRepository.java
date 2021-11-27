package com.AlkemyCB.SpringJavaJwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AlkemyCB.SpringJavaJwt.entity.Movie;
import com.AlkemyCB.SpringJavaJwt.entity.Characters;

@Repository
public interface CharacterRepository extends JpaRepository<Characters, Integer> {
	
	
	public List<Characters> findByName(String name);
	
	public List<Characters> findByAge(int age);
	
	public List<Characters> findByWeight(double weight);
	
	@Query("select pe from Characters pe join pe.movieOrserie p where p.idMovie=(:idMovie)")
	public Movie findByIdMovie(int idMovie);
	
	@Query("select p from Characters p where p.name=(:name) and p.age=(:age)")
	public Characters findByNameAndAge(String name,int age);
	
	@Query("select p from Characters p where p.name=(:name) and p.weight=(:weight)")
	public Characters findByNameAndWeight(String name,double weight);
	
	@Query("select pe from Movie p join p.personajesAsociados pe where pe.name=(:name) and p.idMovie=(:idMovie)")
	public Characters findByNameAndMovie(String name,int idMovie);
	
	

}

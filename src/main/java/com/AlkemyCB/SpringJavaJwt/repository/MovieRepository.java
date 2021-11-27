package com.AlkemyCB.SpringJavaJwt.repository;


import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AlkemyCB.SpringJavaJwt.entity.Movie;


@Repository
public interface MovieRepository  extends JpaRepository<Movie, Integer>{
	
	public Movie findByTitle(String title);
	
	@Query("select m from Movie m  join  m.gender g where g.name=(:gender)")
	public List<Movie> findByName(String gender);
	
	@Query("select p from Movie p  join  p.gender g where g.idGender=(:idGender) and p.title=(:idTitle)")
	public Movie findByGenderid(int idGender, String idTitle);
	
	@Query("select p from Movie p  order by p.creationDate ASC")
	public List<Movie> findByCreationDateAsc();
	
	@Query("select p from Movie p  order by p.creationDate DESC")
	public List<Movie> findByCreationDateDesc();
	

	@Query("select m from Movie m where title=(:title) and creationDate=(:date)")
	public Movie titleandDateRepeted(String title,LocalDate date);
	

}

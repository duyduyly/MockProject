package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

	@Query("SELECT c FROM Candidate c WHERE (:id is null OR c.id = :id) AND"
			+ " (:fullName is null OR c.traineeCandidateProfile.fullName = :fullName) AND"
			+ " (:account is null OR c.account = :account) AND"
			+ " (:dateOfBirth is null OR c.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null OR c.traineeCandidateProfile.phone = :phone) AND"
			+ " (:email is null OR c.traineeCandidateProfile.email = :email)")
	public Page<Candidate> searchCandidate(@Param("id") Integer id, @Param("fullName") String fullName,
			@Param("account") String account, @Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone,
			@Param("email") String email, Pageable pageable);
	
	@Query("SELECT MAX(c.id) FROM Candidate c")
	public Integer getMaxCandidateId();

	Optional<Candidate> findById(Integer id);

	public Long countByAccountContaining(String account);

	@Query("SELECT c FROM Candidate c WHERE (:id is null OR c.id = :id) AND"
			+ " (:fullName is null OR c.traineeCandidateProfile.fullName = :fullName) AND"
			+ " (:account is null OR c.account = :account) AND"
			+ " (:dateOfBirth is null OR c.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null OR c.traineeCandidateProfile.phone = :phone) AND"
			+ " (:email is null OR c.traineeCandidateProfile.email = :email)")
	public List<Candidate> searchListCandidates(@Param("id") Integer id, @Param("fullName") String fullName,
			@Param("account") String account, @Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone,
			@Param("email") String email);

	public Boolean existsByTraineeCandidateProfile_Email(String email);

	public Boolean existsByTraineeCandidateProfile_Phone(String phone);
}

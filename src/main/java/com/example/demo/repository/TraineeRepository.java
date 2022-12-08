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

import com.example.demo.entity.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

	List<Trainee> findAllByTraineeCandidateProfile_DateOfBirth(Date dateOfBirth);

	List<Trainee> findAllByTraineeCandidateProfile_PhoneContaining(String phone);

	List<Trainee> findAllByTraineeCandidateProfile_EmailContaining(String email);

	List<Trainee> findAllById(Integer id);

	List<Trainee> findAllByAccountContaining(String account);

	List<Trainee> findAllByTraineeCandidateProfile_FullNameContaining(String fullName);
	
	Optional<Trainee> findById(Integer id);

	@Query("SELECT t FROM Trainee t WHERE (:id is null or t.id = :id) AND"
			+ " (:account is null or t.account like %:account%) AND"
			+ " (:fullName is null or t.traineeCandidateProfile.fullName like %:fullName%) AND"
			+ " (:dateOfBirth is null or t.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null or t.traineeCandidateProfile.phone like %:phone%) AND"
			+ " (:email is null or t.traineeCandidateProfile.email like %:email%)")
	List<Trainee> findAllBySearchCriteria(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email);
	
	@Query("SELECT t FROM Trainee t WHERE (:id is null or t.id = :id) AND"
			+ " (:account is null or t.account like %:account%) AND"
			+ " (:fullName is null or t.traineeCandidateProfile.fullName like %:fullName%) AND"
			+ " (:dateOfBirth is null or t.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null or t.traineeCandidateProfile.phone like %:phone%) AND"
			+ " (:email is null or t.traineeCandidateProfile.email like %:email%) AND"
			+ " (:classBatchId is null or t.classBatch.id = :classBatchId)")
	List<Trainee> findAllBySearchCriteriaInClass(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email, Integer classBatchId);
	
	@Query("SELECT t FROM Trainee t WHERE (:id is null or t.id = :id) AND"
			+ " (:account is null or t.account like %:account%) AND"
			+ " (:fullName is null or t.traineeCandidateProfile.fullName like %:fullName%) AND"
			+ " (:dateOfBirth is null or t.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null or t.traineeCandidateProfile.phone like %:phone%) AND"
			+ " (:email is null or t.traineeCandidateProfile.email like %:email%) AND"
			+ " ((:waitingForClass is null or t.status.statusName = :waitingForClass) OR"
			+ "  (:waitingForAllocation is null or t.status.statusName = :waitingForAllocation) OR"
			+ "  (:deferred is null or t.status.statusName = :deferred) OR "
			+ "  (:dropOut is null or t.status.statusName = :dropOut))")
	List<Trainee> findAllBySearchCriteriaToAdd(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email, String waitingForClass, String waitingForAllocation, String deferred, String dropOut);
	
	Long countByAccountContaining(String account);
	
	Long countByStatusInClassIsAndClassBatch_IdIs(String statusInClass, Integer classBatchId); 
	
	@Query("SELECT t FROM Trainee t WHERE (:id is null OR t.id = :id) AND"
			+ " (:fullName is null OR t.traineeCandidateProfile.fullName = :fullName) AND"
			+ " (:account is null OR t.account = :account) AND"
			+ " (:dateOfBirth is null OR t.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null OR t.traineeCandidateProfile.phone = :phone) AND"
			+ " (:email is null OR t.traineeCandidateProfile.email = :email)")
	public Page<Trainee> searchTrainee(@Param("id") Integer id, @Param("fullName") String fullName,  @Param("account") String account,
			@Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone, @Param("email") String email,
			Pageable pageable);

	@Query("SELECT t FROM Trainee t WHERE (:id is null OR t.id = :id) AND"
			+ " (:fullName is null OR t.traineeCandidateProfile.fullName = :fullName) AND"
			+ " (:account is null OR t.account = :account) AND"
			+ " (:dateOfBirth is null OR t.traineeCandidateProfile.dateOfBirth = :dateOfBirth) AND"
			+ " (:phone is null OR t.traineeCandidateProfile.phone = :phone) AND"
			+ " (:email is null OR t.traineeCandidateProfile.email = :email)")
	public List<Trainee> searchListTrainee(@Param("id") Integer id, @Param("fullName") String fullName,  @Param("account") String account,
			@Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone, @Param("email") String email);

	Boolean existsByTraineeCandidateProfile_Email(String email);

	Boolean existsByTraineeCandidateProfile_Phone(String phone);

}

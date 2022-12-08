package com.example.demo.service.iservice;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Trainee;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.model.TraineeUpdateModel;

public interface ITraineeService {

	List<Trainee> filterTraineeSearchCriteria(TraineeCriteriaModel traineeCriteriaModel);

	List<Trainee> filterTraineeSearchCriteriaToAdd(TraineeCriteriaModel traineeCriteriaModel);

	List<Trainee> filterTraineeSearchCriteriaInClass(TraineeCriteriaModel traineeCriteriaModel, Integer classBatchId);
	
	Optional<Trainee> findById(Integer id);

	List<Trainee> findAllBySearchCriteria(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email);
	
	List<Trainee> findAllBySearchCriteriaInClass(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email, Integer classBatchId);
	
	List<Trainee> findAllBySearchCriteriaToAdd(Integer id, String account, String fullName, Date dateOfBirth, String phone,
			String email, String waitingForClass, String waitingForAllocation, String deferred, String dropOut);
	
	Long countByAccountContaining(String account);
	
	Long countByStatusInClassIsAndClassBatch_IdIs(String statusInClass, Integer classBatchId); 
	
	Page<Trainee> searchTrainee(@Param("id") Integer id, @Param("fullName") String fullName,  @Param("account") String account,
			@Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone, @Param("email") String email,
			Pageable pageable);

	Trainee save(Trainee trainee);

	void deleteTrainee(String traineeId);

	Page<Trainee> findAllTrainee(int currentPage, int size);

	Page<Trainee> searchTrainee(int currentPage, int size, TraineeCriteriaModel traineeCriteriaModel);

	void update(TraineeUpdateModel traineeUpdateModel);
	
	public List<Trainee> searchListTrainee(@Param("id") Integer id, @Param("fullName") String fullName,  @Param("account") String account,
			@Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone, @Param("email") String email);

	List<Trainee> searchListTrainee(TraineeCriteriaModel traineeCriteriaModel);

	List<Trainee> findAll();

	boolean checkIfTraineeExisted(Trainee trainee);

	List<Trainee> saveAll(List<Trainee> listOfTrainees);
}

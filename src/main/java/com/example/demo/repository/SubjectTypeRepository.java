package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SubjectType;

@Repository
public interface SubjectTypeRepository extends JpaRepository<SubjectType, Integer>{

	SubjectType findBySubjectTypeName(String subjectTypeName);
}

package com.dcb.spring.data.jpa.repository;

import com.dcb.spring.data.jpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student,Long> {
    // for naming methods refer - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    List<Student> findByFirstName(String firstName);

    List<Student> findByFirstNameContaining(String name);

    List<Student> findByLastNameNotNull();

    //'GuardianName' - Guardian comes from attribute in Student class and Name comes from that in Guardian class, both in camel case
    List<Student> findByGuardianName(String guardianName);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);

    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    String getStudentFirstNameByEmailAddress(String emailId);

    //Native
    @Query(
            value = "SELECT * FROM tbl_student s where s.email_address = ?1", nativeQuery = true)
    Student getStudentByEmailAddressNative(String emailId);

    //Native Named Param
    @Query(value = "SELECT * FROM tbl_student s where s.email_address = :emailId", nativeQuery = true)
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    @Modifying // to update or delete in db
    @Transactional  // to commit transactions into service layer
    @Query(value = "update tbl_student set first_name = ?1 where email_address = ?2", nativeQuery = true)
    int updateStudentNameByEmailId(String firstName, String emailId);

}

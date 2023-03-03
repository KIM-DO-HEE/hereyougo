package io.k2c1.hereyougo.repository;

import io.k2c1.hereyougo.domain.Appointment;
import io.k2c1.hereyougo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
    @Query(value = "SELECT distinct a FROM Appointment a INNER JOIN a.post p WHERE p.writer = :writer_id OR a.wanted = :member_id")
    List<Appointment> getAppointments(@Param("writer_id") Member writer_id, @Param("member_id") Member member_id);
}

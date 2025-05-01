package com.ryu.escaping.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryu.escaping.reservation.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer>{

}

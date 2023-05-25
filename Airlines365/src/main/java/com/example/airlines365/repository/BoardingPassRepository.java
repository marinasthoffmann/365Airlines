package com.example.airlines365.repository;

import com.example.airlines365.model.BoardingPass;
import com.example.airlines365.model.enums.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingPassRepository extends JpaRepository<BoardingPass, String> {

    boolean existsByAssento(Seat assento);

}

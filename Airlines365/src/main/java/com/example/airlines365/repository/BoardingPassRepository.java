package com.example.airlines365.repository;

import com.example.airlines365.model.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingPassRepository extends JpaRepository<BoardingPass, String> {

    BoardingPass findBoardingPassByPassageiro_Cpf(Long cpf);

}

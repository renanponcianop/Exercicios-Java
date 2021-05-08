package com.exercicos.ex5;

import com.exercicos.ex5.entities.Veiculo;
import com.exercicos.ex5.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.Arrays;
import java.util.Calendar;

@SpringBootApplication
public class Ex5Application implements CommandLineRunner {

	@Autowired
	VeiculoRepository veiculoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Ex5Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//prenchendo o banco para facilitar nos testes e no desenvolvimento
		Veiculo v1 = new Veiculo(null,"Model 3", "Tesla", 2021, "Carro elétrico da marca Tesla", false);
		Veiculo v2 = new Veiculo(null,"Model X", "Tesla", 2019, "Carro elétrico da marca Tesla", false);
		Veiculo v3 = new Veiculo(null,"Model Y", "Tesla", 2020, "Carro elétrico da marca Tesla", true);
		Veiculo v4 = new Veiculo(null,"Model S", "Tesla", 2019, "Carro elétrico da marca Tesla", true);
		veiculoRepository.saveAll(Arrays.asList(v1,v2,v3,v4));
	}
}

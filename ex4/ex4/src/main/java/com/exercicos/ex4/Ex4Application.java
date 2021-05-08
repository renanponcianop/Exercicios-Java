package com.exercicos.ex4;

import java.util.Scanner;

public class Ex4Application {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.print("Insira um número positivo:");
		int inteiro = s.nextInt();
		int aux = 0;
		System.out.println("Lista dos números:");
		for (int i = 1; i < inteiro; i++){
			if (i % 3 == 0 || i % 5 == 0){
				aux += i;
				System.out.print(i+" - ");
			}
		}
		System.out.printf("%nA soma dos números divisíveis por 3 ou 5 de "+inteiro+" é " + aux);

	}

}

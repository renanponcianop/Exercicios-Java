package com.exercicos.ex3;

import java.util.Scanner;

public class Ex3Application {

	public static void main(String[] args) {
		System.out.println("Digite um número positivo:");
		Scanner s = new Scanner(System.in);
		int inteiro = s.nextInt();
		int aux = 1;
		int fatorial = 1;
		//se a entrada for 0, o while nem é executado e já mostra o resultado de 1
		while (aux <= inteiro){
			fatorial = fatorial * aux;
			aux++;
		}
		System.out.println("O fatorial de "+ inteiro +" é " + fatorial);
	}

}

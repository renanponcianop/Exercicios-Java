package com.exercicios.ex1;

public class Ex1Application {

	public static void main(String[] args) {
		int total = 1000;
		int validos = 800;
		int brancos = 150;
		int nulos = 50;

		System.out.println("O percentual de votos válidos foi de " + getPocentagem(validos, total) + "%");
		System.out.println("O percentual de votos brancos foi de " + getPocentagem(brancos, total) + "%");
		System.out.println("O percentual de votos nulos foi de " + getPocentagem(nulos, total)+ "%");
	}

	public static float getPocentagem(int quantidade, int total){
		return  ((quantidade * 100) / total);
	}

}

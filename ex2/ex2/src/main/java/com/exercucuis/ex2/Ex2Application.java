package com.exercucuis.ex2;


public class Ex2Application {

	public static void main(String[] args) {

		int[] v = {5, 3, 2, 4, 7, 1, 0, 6};

		System.out.printf("Vetor sem tratamento: ");
		imprimeVetor(v);
		bubbleSort(v);
		System.out.printf("%nVetor pós Bubble Sort: ");
		imprimeVetor(v);

	}

	public static void bubbleSort(int[] v){
		boolean precisouOrdenar = true;
		while (precisouOrdenar){
			precisouOrdenar = false;
			for(int i = 0; i < v.length - 1; i++ ){
				if(v[i] > v[i + 1]){
					int temp = v[i + 1];
					v[i + 1] = v[i];
					v[i] = temp;
					precisouOrdenar = true;
				}
			}
		}
	}

	public static void imprimeVetor(int[] v){
		for(int i = 0; i < v.length; i++ ) {
			System.out.print(v[i]+" ");
		}
	}

}

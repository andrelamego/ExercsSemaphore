package ex3;

import java.util.concurrent.Semaphore;

public class Transacoes {
	public static void main(String[] args) {
		Semaphore semafSaque = new Semaphore(1);
		Semaphore semafDeposito = new Semaphore(1);
			
		
		for (int i = 0; i < 20; i++) {
			
			String op = "";
			int codigo = (int)((Math.random() * 10) + 1);
			int valor = (int)(Math.random() * 2000);
			double[] saldo = {5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000, 5000};
			
			int qtdOp = (int)(Math.random() * 100);
			
			if(qtdOp % 2 == 0) {
				op = "saque";
				Banco operacao = new Banco(op, codigo, saldo, valor, semafSaque);
				operacao.start();
			}
			else {
				op = "deposito";
				Banco operacao = new Banco(op, codigo, saldo, valor, semafDeposito);
				operacao.start();
			}	
		}
		
		
	}
}

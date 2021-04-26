package barbeiro_src;

import java.util.Random;

public class Barbeiro implements Runnable {

	private int cadeiraDeEspera;
	boolean cadeiraOcupada = false;
	int[] clientes;
	boolean barbeiroDorme = false;
	private String nome;
	private int cliNovos;
	int nClientes = 0;
	
	
	Barbeiro(String nome, int cadeiraDeEspera, int clientes){
		cliNovos = clientes;
		this.nome = nome;
		this.cadeiraDeEspera = cadeiraDeEspera;
		System.out.println("O barbeiro " + nome + "chegou no sal�o");
	}
	
	public void Clientes() {
		Random r = new Random();
		nClientes = r.nextInt(cliNovos);
		clientes = new int[nClientes];
		
		
		for (int i = 0; nClientes < clientes.length; i++) {
			clientes[i] = i;
		}
	}
	
	public void BarbeiroDorme() throws InterruptedException {
		System.out.println("N�o existe(m) cliente(s) no sal�o do Barbeiro " + nome + ".");
		System.out.println("O barbeiro "+nome+ "est� esperando a chegada de clientes.");
		Thread.sleep(2000);
		System.out.println("A cadeira do Barbeiro "+ nome + "est� livre");
		
		Clientes();
	}
	
	public void BarbeiroAtende() throws InterruptedException {
		
		if(nClientes != 0) {
			if (nClientes > 1 && cadeiraOcupada == false) {
				System.out.println("Entrou(aram) "+ nClientes +"cliente(s) no sal�o");
			}
			else {
				System.out.println("Existe(m) "+ nClientes +"cliente(s) esperando atendimento");
			}
		}
		
		System.out.println("Um cliente ocupa a cadeira do barbeiro " +nome);
		nClientes--;
		System.out.println("Um cliente esta sendo atendido pelo barbeiro " +nome);
		cadeiraOcupada = true;
		
		Thread.sleep(1000);
		
		if(nClientes > cadeiraDeEspera) {
			
			int cli = nClientes - cadeiraDeEspera;
			
			nClientes = nClientes - cli;
			
			for (int i = 0; i < clientes.length -1; i++) {
				clientes[i] = 0;
			}
			for (int j = 0; j < nClientes -1; j++) {
				clientes[j] = j + 1;
			}
			
			System.out.println(cli + " clientes foram embora");
			
			System.out.println(nClientes + " clientes estao esperando");
			
		}
		else if(nClientes == 1) {
			System.out.println("A cadeira do barbeiro" +nome + "est� livre");
			
			System.out.println("A cadeira do barbeiro" +nome + "est� ocupada, nao existem clientes esperando");
			Thread.sleep(1000);
			nClientes--;
			
			System.out.println("Um cliente ja foi atendido pelo barbeiro "+ nome);
		}
		else {
			System.out.println("A cadeira do barbeiro "+ nome + "esta livre");
			
			cadeiraOcupada = false;
		}
		
		
		
	}
	
	@Override
	public void run() {
		while (true) {
			if(nClientes <= 0) {
				try {
					BarbeiroDorme();
				} catch (InterruptedException ex) {
					System.out.println(ex);
				}
			}
			else {
				try {
					BarbeiroAtende();	
				}catch (InterruptedException ex) {
					System.out.println(ex);
				}
			}
		}
		
	}

}

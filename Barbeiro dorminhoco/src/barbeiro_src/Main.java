package barbeiro_src;

public class Main {
	public static void main(String[] args) {
		Barbeiro barbeiro1 = new Barbeiro("Joao", 5, 5);
		
		Thread threadBarbeiro1 = new Thread(barbeiro1);
		
		threadBarbeiro1.start();
	}
}

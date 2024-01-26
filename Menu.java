package FlightReservation;

public class Menu {
	public void mainMenu() {
		System.out.println("\n+-----------------------+");
		System.out.println("|   SELAMAT DATANG! ^^  |");
		System.out.println("+-----------------------+");
		System.out.println("| [1] PESAN TIKET       |");
		System.out.println("| [2] BAYAR TIKET       |");
		System.out.println("| [3] PEMBATALAN TIKET  |");
		System.out.println("+-----------------------+");
		System.out.println("| [4] SHUT DOWN         |");
		System.out.println("+-----------------------+");
		System.out.print("PILIH : ");
	}
	
	public void admin() {
		System.out.println("\n+-----------------------+");
		System.out.println("|   HALO ADMIN! ^^      |");
		System.out.println("+-----------------------+");
		System.out.println("|    --- MASKAPAI ---   |");
		System.out.println("| [1] TAMBAH            |");
		System.out.println("| [2] HAPUS             |");
		System.out.println("|    --- DESTINASI ---  |");
		System.out.println("| [3] TAMBAH            |");
		System.out.println("| [4] HAPUS             |");
		System.out.println("+-----------------------+");
		System.out.println("| [99] SELESAI          |");
		System.out.println("+-----------------------+");
		
	}
	
	public void menuPilihan(byte pilihMenu) {
		switch (pilihMenu) {
		case 1 : 
			System.out.println("\n+----------------------------------+");
			System.out.println("| PILIHAN ANDA : [1] PESAN TIKET   |");
			System.out.println("+----------------------------------+");
			break;
		case 2 :
			System.out.println("\n+----------------------------------+");
			System.out.println("| PILIHAN ANDA : [2] BAYAR TIKET   |");
			System.out.println("+----------------------------------+");
			break;
		case 3 :
			System.out.println("\n+---------------------------------------+");
			System.out.println("| PILIHAN ANDA : [3] PEMBATALAN TIKET   |");
			System.out.println("+---------------------------------------+");
			break;
		case 4 :
			System.out.println("\n+---------------------------------------+");
			System.out.println("| PILIHAN ANDA : [4] SHUT DOWN          |");
			System.out.println("+---------------------------------------+");
			break;
		case 78 :
			System.out.println("\n+---------------------------------------+");
			System.out.println("| ANDA MEMASUKI MODE ADMIN 🛠           |");
			System.out.println("+---------------------------------------+");
			break;
			
		default :
			System.out.println("\nINPUT SALAH!");
		}
	}
}

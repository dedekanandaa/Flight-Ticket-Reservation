package FlightReservation;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//TAMBAH TANGGAL
		//PISAH TIKET PENERBANGAN
		
		Scanner input = new Scanner(System.in);

		Destinasi dest = new Destinasi(null, null, 0); //INISIASI OBJECT DESTINASI
		dest.data();
		
		Maskapai mask = new Maskapai(null, null, 0); //INISIASI OBJECT MASKAPAI
		mask.data();
		
		TiketBayar tiketB = new TiketBayar(0, 0, 0, null, (byte) 0, false);
		
		TiketPenerbangan tiketP = new TiketPenerbangan(0, 0, 0, null, null);
		
		Menu menu = new Menu();
		
		byte pilihMenu= 0;
		byte pilihTiket = 0;
		byte coba = 0;
		String shutDown = "N";

		while (pilihMenu != 4 || !shutDown.toUpperCase().equals("Y")) {
			
			// MAIN MENU
			if (pilihMenu == 0) {
				menu.mainMenu(); // CETAK MAIN MENU
				pilihMenu = input.nextByte();
				menu.menuPilihan(pilihMenu); // CETAK PILIHAN USER
			} else if (pilihMenu == 1) {
				//MAIN MENU PILIH [1]
				
				pilihMenu = 0;
				byte pilihDest = 0;
				
				while (pilihDest != dest.banyakArray()+1) { // SAAT USER TIDAK MEMILIH [KEMBALI]
					
					//PILIH DESTINASI
					dest.cetak(); // CETAK MENU DESTINASI YANG TERDAFTAR
					pilihDest = input.nextByte();
					
					if (pilihDest <= dest.banyakArray() && pilihDest > 0) { // JIKA INPUTAN PILIH DESTINASI BENAR
						
						//TANGGAL BERAPA
						System.out.print("TANGGAL (1-31) : ");
						byte hari = input.nextByte();
						System.out.print("BULAN (1-12) : ");
						byte bulan = input.nextByte();
						System.out.print("TAHUN : ");
						int tahun = input.nextInt();
						String tgl = Byte.toString(hari) + "-" + Byte.toString(bulan) + "-" + Integer.toString(tahun);
						
						System.out.println(tgl);
						//BANYAK TIKET YANG DIBELI 
						byte banyakTiket = 0;
						
						while (banyakTiket != 88) { //SAAT USER TIDAK MEMILIH [KEMBALI]
							
							dest.cetakPilihan((byte)(pilihDest-1));
							banyakTiket = input.nextByte();
							
							if(banyakTiket == 99 || banyakTiket == 88) { //PROSES CANCEL SAAT MEMASUKKAN BANYAK TIKET
								
								System.out.println("APAKAH ANDA YAKIN UNTUK KEMBALI?");
								System.out.print("YAKIN? (Y/N) : ");
								char yakin;
								yakin = input.next().charAt(0);
								
								if (Character.toUpperCase(yakin) == 'Y' && banyakTiket == 99) {
									
									//CANCEL
									pilihMenu = 0;
									banyakTiket = 88;
									pilihDest = (byte) (dest.banyakArray() + 1);	
								}
							} else { //BANYAK TIKET YANG DIMASUKKAN SESUAI
								
								System.out.println("\n+----------------------------+-------+");
								System.out.println("| " + dest.pilihan(pilihDest-1) + "  " + banyakTiket + "x   |"); //DESTINASI YANG DIPILIH DAN BANYAK TIKETNYA
								System.out.println("+----------------------------+-------+");

								if (pilihDest != 88 || pilihDest != 99) { 
									
									byte pilihMask = 0;
									
									while (pilihMask != 88) { // JIKA TIDAK CANCEL SAAT PILIH TIKET
										
										//PILIH MASKAPAI
										mask.cetak(pilihDest, tgl, dest.getArrayList(), tiketB.getArrayList());										
										
										pilihMask = input.nextByte();
										
										if(pilihMask== 99 || pilihMask== 88) { //PROSES CANCEL PADA SAAT MEMILIH TIKET
											System.out.println("APAKAH ANDA YAKIN UNTUK KEMBALI?");
											System.out.print("YAKIN? (Y/N) : ");
											char yakin;
											yakin = input.next().charAt(0);
											
											if (Character.toUpperCase(yakin) == 'Y' && pilihMask== 99) {
												//CANCEL
												pilihMenu = 0;
												pilihMask= 88;
												banyakTiket = 88;
												pilihDest = (byte) (dest.banyakArray() + 1);
												
											}
											
										} else if (tiketB.getBanyakTiket(pilihDest, pilihMask, tgl) >= 20) { //JIKA MEMILIH MASKAPAI YANG TIKETNYA SUDAH HABIS
											
											System.out.println("\nTIKET SUDAH HABIS! SILAHKAN PILIH MASKAPAI YANG LAIN\n");
											
										} else { //JIKA MEMILIH MASKAPAI
											
											if (banyakTiket > (20 - tiketB.getBanyakTiket(pilihDest, pilihMask, tgl))) { // JIKA BANYAK TIKET > SISA TIKET
												
												System.out.println("\nTIKET YANG ANDA PESAN MELEBIHI KETERSEDIAAN TIKET");
												System.out.println("SILAHKAN PILIH MASKAPAI YANG LAIN\n");
											
											} else { // PROSES TAMBAH TIKET
												
												tiketB.tambah(pilihDest, pilihMask, tgl, banyakTiket);
												tiketB.cetak(tiketB.banyakArray()-1, dest.getArrayList(), mask.getArrayList());
												tiketB.cetak();
												pilihTiket = input.nextByte();
												
												if (pilihTiket != 2) {
													
													System.out.println("\n+--------------------------------+");
													System.out.println("|          TERIMAKASIH ^^        |");
													System.out.println("+--------------------------------+");
													
													pilihMenu = 0;
													pilihMask = 88;
													banyakTiket = 88;
													pilihDest = (byte) (dest.banyakArray() + 1);
													
												} else {
													
													pilihMenu = 2;
													pilihMask= 88;
													banyakTiket = 88;
													pilihDest = (byte) (dest.banyakArray() + 1);
													
												}
											}
										} 
									} // WHILE PILIH MASK  
								} // IF TIDAK KEMBALI
							} // DESTINASI SESUAI
						} // WHILE BANYAK TIKET
						
					} else if (pilihDest == dest.banyakArray()+1) { 
						
						//PILIH DESTINASI KEMBALI
						System.out.println("\n[" + pilihDest + "] KEMBALI\n");
						
					} else { 
						
						//PILIH DESTINASI SALAH INPUT
						System.out.println("INPUT SALAH!\n");
					}					
				} 
				
			} else if (pilihMenu == 2) {
				
				input.nextLine();
				pilihMenu = 0;
				String kodeBayar = "A";
				
				
				if (pilihTiket == 2) { // PILIH MENU BAYAR DARI PESAN TIKET
					
					pilihTiket = 0;
					int a = tiketB.banyakArray(); // AMBIL TIKET YANG TERAKHIR DIMASUKKAN KE ARRAYLIST
					kodeBayar = tiketB.getId(a); // MENGAMBIL ID DARI INDEX TERAKHIR
					
					int index = tiketB.cariTiket(kodeBayar); // MENCARI KODE SESUAI ID
					tiketB.cetak(index, dest.getArrayList(), mask.getArrayList());
					
					int bayar = 0;
					byte banyakTiket = tiketB.getBanyak(a-1);
					int des = tiketB.getKodeDest(a-1);
					int mas = tiketB.getKodeMask(a-1);
					String tanggal = tiketB.getTanggal(a-1);
					
					while (!tiketB.getBayar(index) && bayar != 88) { // PERULANGAN JIKA UANG TIDAK CUKUP
						
						System.out.println("[88] KEMBALI : ");
						System.out.print("MASUKKAN JUMLAH UANG : ");
						bayar = input.nextInt();
						tiketB.bayarTiket(index, mask.getArrayList(), dest.getArrayList(), bayar); // CEK VALIDASI INCLUDED	
						
						if (tiketB.getBayar(index)) {
							
							for(int i = 0; i < banyakTiket; i++) {
								
								tiketP.tambah(des, mas, tanggal);
								tiketP.cetak(dest.getArrayList(), mask.getArrayList());									
								
							}
						}
					}
				
				} else { // PILIH MENU BAYAR DARI MAIN MENU
					
					while (!kodeBayar.toUpperCase().equals("BATAL")) {
						//
						System.out.println("KETIK [BATAL] UNTUK KEMBALI: ");
						System.out.print("MASUKKAN KODE BAYAR : ");
						kodeBayar = input.nextLine();
						int index = tiketB.cariTiket(kodeBayar.toUpperCase());
						// MENCARI TIKET
						
						if (index >= 0) {
							
							if (!tiketB.getBayar(index)) {
								//
								int bayar = 0;
								byte des = (byte) (tiketB.getKodeDest(index));
								byte mas = (byte) (tiketB.getKodeMask(index));
								String tgl = (tiketB.getTanggal(index));
								int banyakTiket = tiketB.getBanyakTiket(des, mas, tgl);
								int sisa = (20-banyakTiket);
								// INISIASI VARIABLE
								
								if (sisa > tiketB.getBanyak(index)) {
									
									while (!tiketB.getBayar(index) && bayar != 88) {
										//
										tiketB.cetak(index, dest.getArrayList(), mask.getArrayList());
										System.out.println("[88] KEMBALI : ");
										System.out.print("MASUKKAN JUMLAH UANG : ");
										bayar = input.nextInt();
										tiketB.bayarTiket(index, mask.getArrayList(), dest.getArrayList(), bayar);
										
										if (tiketB.getBayar(index)) {
											
											for(int i = 0; i < banyakTiket; i++) {
												
												tiketP.tambah(des, mas, tgl);
												tiketP.cetak(dest.getArrayList(), mask.getArrayList());		
												
											}
										}
										
										kodeBayar = "BATAL";
										// BAYAR
										
									}
									
								} else if (sisa == 0){
									
									System.out.println("\nMAAF TIKET SUDAH HABIS! ^^\n");
									kodeBayar = "BATAL";
									
								} else {
									
									tiketB.cetak(index, dest.getArrayList(), mask.getArrayList());
									System.out.println("MAAF SISA TIKET TIDAK MEMENUHI PERMINTAAN ANDA");
									System.out.println("SISA : " + sisa);
									kodeBayar = "BATAL";
									
								}
								
							} else {
								
								System.out.println("+--------------------------+");
								System.out.println("|   TIKET SUDAH DIBAYAR!   |");
								System.out.println("+--------------------------+");
								kodeBayar = "BATAL";
								
							}
							
						} else if (!kodeBayar.toUpperCase().equals("BATAL")){
							
							System.out.println("KODE BAYAR SALAH!");
						}
					}
				}
				
			} else if (pilihMenu == 3) {
				
				pilihMenu = 0;
				String kodeBayar = "a";
				
				System.out.println("JIKA TIKET SUDAH DIBAYAR, UANG TIDAK AKAN DIKEMBALIKAN");
				System.out.print("APAKAH ANDA YAKIN? (Y/N) :");
				char yakin1 = input.next().charAt(0);
				
				if (Character.toUpperCase(yakin1) == 'Y') {
					
					input.nextLine();
					while (!kodeBayar.toUpperCase().equals("BATAL")) {
						
						System.out.println("\nKETIK [BATAL] UNTUK KEMBALI");
						System.out.print("MASUKKAN KODE TIKET : ");
						kodeBayar = input.nextLine();
						int index = tiketB.cariTiket(kodeBayar.toUpperCase());
						
						if (index >= 0) {
							
							tiketB.cetak(index, dest.getArrayList(), mask.getArrayList());
							System.out.println("APAKAH TIKET SUDAH BENAR? (Y/N)");
							char yakin2 = input.next().charAt(0);
							
							if (Character.toUpperCase(yakin2) == 'Y') {
								
								tiketB.hapus(index);
								kodeBayar = "BATAL";
								
							}
						} else if (!kodeBayar.toUpperCase().equals("BATAL")){
							
							System.out.println("TIKET TIDAK DITEMUKAN!, SILAHKAN MASUKKAN ULANG");
							
						}
					}
				}
				
			} else if (pilihMenu == 4) {
				
				input.nextLine();
				System.out.print("\nMASUKKAN PASSWORD : ");
				String password = input.nextLine();
				
				if (password.equals("admin")) {
					
					System.out.println("APAKAH ANDA YAKIN UNTUK MEMATIKAN SISTEM?");
					System.out.print("YAKIN ? (Y/N) : ");
					shutDown = input.nextLine();
					
					if (!shutDown.toUpperCase().equals("Y")) {
						
						System.out.println("\nSISTEM TETAP BERJALAN ^^");
						pilihMenu = 0;
						
					}
					
				} else {
					System.out.println("\nPASSWORD SALAH, SISTEM AKAN TETAP BERJALAN\n");
					pilihMenu = 0;
				}
				
			} else if (pilihMenu == 78) {
				
				pilihMenu = 0;
				
				if (coba < 3) {
					
					input.nextLine();
					System.out.print("\nMASUKKAN PASSWORD : ");
					String password = input.nextLine();
					
					if (password.equals("admin")) {
						
						byte pilihAdmin = 0;
			
						while (pilihAdmin != 99) {
							menu.admin();
							pilihAdmin = input.nextByte();
						
							if (pilihAdmin == 1) {
							
								input.nextLine();
								System.out.print("NAMA MASKAPAI : ");
								String nama = input.nextLine();
								System.out.print("JAM KEBERANGKATAN MASKAPAI : ");
								String jamKeberangkatan = input.nextLine();
								System.out.print("HARGA MASKAPAI : ");
								int harga = input.nextInt();
								mask.tambah(nama, jamKeberangkatan, harga);
						
							} else if (pilihAdmin == 2) {
							
								mask.cetak();
								int index = input.nextInt()-1;	
								mask.hapus(index);
							
							} else if (pilihAdmin == 3) {
								
								input.nextLine();
								System.out.print("ASAL DESTINASI : ");
								String asal = input.nextLine();
								System.out.print("TUJUAN DESTINASI : ");
								String tujuan = input.nextLine();
								System.out.print("BERAPA JAM PERJALANAN? : ");
								double jamPerjalanan = input.nextDouble();
								dest.tambah(asal, tujuan, jamPerjalanan);
								
							} else if (pilihAdmin == 4) {
								
								dest.cetak();
								int index = input.nextInt()-1;
								dest.hapus(index);
								
							} else if (pilihAdmin == 99){
								
							} else {
								System.out.println("INPUT SALAH!");
							}
						}
						
						
					} else {
						
						coba++;
						
						if (coba != 3) {
							
							System.out.println("\nPASSWORD SALAH! SISA PERCOBAAN : ("+ coba + "/3)");
							
						} else {
							
							System.out.println("SISA PERCOBAAN SUDAH HABIS");
							
						}
					}
				} else {
					
					System.out.println("SISA PERCOBAAN SUDAH HABIS");
					
				}
				
			} else if (pilihMenu < 1 || pilihMenu > 4 ) {
				
				pilihMenu = 0; 
			} 
		}
	}
}
// SELESAI JUGA :)
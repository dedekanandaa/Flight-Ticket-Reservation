package FlightReservation;

import java.util.ArrayList;

public class Destinasi extends DataPenerbangan implements IsiData{
	private String asal, tujuan;
	double jamPerjalanan;
	
	public String getAsal() {
		return asal;
	}
	public String getTujuan() {
		return tujuan;
	}
	
	public Destinasi(String asal, String tujuan, double jamPerjalanan) {
		this.asal = asal;
		this.tujuan = tujuan;
		this.jamPerjalanan = jamPerjalanan;
	}
	
	ArrayList<Destinasi> dest = new ArrayList<Destinasi>();
	public ArrayList<Destinasi> getArrayList() {
		return dest;
	}
	
	@Override
	public void tambah(String asal, String tujuan, double jamPerjalanan) {
		dest.add(new Destinasi(asal, tujuan, jamPerjalanan));
	}
	
	public void data() {
		dest.add(new Destinasi("BALI", "DURIAN RUNTUH", 2.1)); //MENGISI ARRAYLIST PADA OBJECT
		dest.add(new Destinasi("BALI", "BEKASI", 3.4));
		dest.add(new Destinasi("BALI", "BELANDA", 9.7));
	}
	
	@Override
	public void hapus(int index) {
		dest.remove(index);
		System.out.println("+-----------------------------+");
		System.out.println("| DESTINASI BERHASIL DIHAPUS! |");
		System.out.println("+-----------------------------+");
	}
	
	public int banyakArray() {
		return dest.size();
	}
	
	public void cetak() {
		System.out.println("\n+----------------------------+");
		System.out.println("|      PILIH DESTINASI       |");
		System.out.println("+----------------------------+");

		//PERULANGAN MENCETAK
		for (byte i = 0; i < dest.size(); i++) {
			String space = "";
			String temp = dest.get(i).asal + " - " + dest.get(i).tujuan;
			
			//MENGHITUNG TUTUP PADA TABEL
			for (int j = temp.length(); j <= 22; j++) {
				space = space + " "; 
			}
			System.out.println("| ["+ (i+1) +"] " + temp + space + "|");
		}
		
		System.out.println("+----------------------------+");
		System.out.println("| ["+ (dest.size()+1) +"] Kembali                |");
		System.out.println("+----------------------------+");
		
		System.out.print("PILIH : ");
	}
	
	
	public String pilihan(int i) {
		String space = "";
		String temp = dest.get(i).asal + " - " + dest.get(i).tujuan;
		
		for (int j = temp.length(); j <= 22; j++) {
			space = space + " "; 
		}
		return ("["+ (i+1) +"] " + temp + space + "|");
		
	}
	
	public void cetakPilihan(byte pilihDest) {
		System.out.println("+-------------------------------------------+");
		System.out.println("| ANDA MEMILIH : " + pilihan(pilihDest)); //DESTINASI YANG DIPILIH SEBELUMNYA
		System.out.println("+-------------------------------------------+");
		System.out.println("[88] SALAH PILIH DESTINASI?");
		System.out.println("[99] BATAL\n");
		
		System.out.print("BELI BERAPA TIKET? : ");
	}
}
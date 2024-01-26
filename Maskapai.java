package FlightReservation;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Maskapai extends DataPenerbangan implements IsiData {
	private String nama, jamKeberangkatan;
	private double harga;
	
	public String getNama() {
		return nama;
	}
	public String getJamKeberangkatan() {
		return jamKeberangkatan;
	}	
	public double getHarga() {
		return harga;
	}
	
	public Maskapai (String nama, String jamKeberangkatan, double harga) {
		this.nama = nama;
		this.jamKeberangkatan = jamKeberangkatan;
		this.harga = harga;
	}
	
	public int banyakArray() {
		return mask.size();
	}
	
	ArrayList<Maskapai> mask = new ArrayList<Maskapai>();
	public ArrayList<Maskapai> getArrayList() {
		return mask;
	}
	
	public void data() {
		mask.add(new Maskapai("Udara Asia", "17:00", 213402));	// MENGISI ARRAYLIST MASKAPAI
		mask.add(new Maskapai("Singa Udara", "09:00", 175609));
		mask.add(new Maskapai("Burung Indonesia", "13:00", 199801));
	}
	
	@Override
	public void tambah(String nama, String jamKeberangkatan, double harga) {
		mask.add(new Maskapai(nama, jamKeberangkatan, harga));
		
		System.out.println("\nMASKAPAI BERHASIL DITAMBAHKAN!");
	}
	
	
	@Override
	public void hapus(int index) {
		mask.remove(index);
		System.out.println("+----------------------------+");
		System.out.println("| MASKAPAI BERHASIL DIHAPUS! |");
		System.out.println("+----------------------------+");
	}
	public void cetak(byte kodeDes, String tanggal, ArrayList<Destinasi> dest, ArrayList<TiketBayar> tiket) {
		System.out.println("+-----+-------------------+-------------------+----------------+----------------+");
		System.out.println("|     |     Maskapai      | Jam Keberangkatan |      Harga     | Tiket Tersedia |");
		System.out.println("+-----+-------------------+-------------------+----------------+----------------+");
		
		for (byte i = 0; i < mask.size();i++) {
			DecimalFormat df = new DecimalFormat("###,###");
			
			//
			String space = "";
			String temp = mask.get(i).nama;
			for(int j = temp.length(); j <= 16; j++) {
				space = space + " ";
			}
			String nama = temp + space + " | "; // CETAK NAMA
			
			//
			space = "";
			temp = mask.get(i).jamKeberangkatan + " WITA";
			for(int j = temp.length(); j <=16; j++) {
				space = space + " ";
			}
			String jamKeberangkatan = temp + space + " | "; //CETAK JAM KEBERANGKATAN
			
			//
			space = "";
			double hrg = mask.get(i).harga * dest.get(kodeDes-1).jamPerjalanan;
			temp = "Rp. " + df.format(hrg);
			for(int j = temp.length(); j <=13; j++) {
				space = space + " ";
			}
			String harga = temp + space + " | "; //CETAK HARGA
			
			//
			space = "";
			int banyak = 0;
			if (tiket.size()==0) {
				temp = "20x";				
			} else {
				for (byte j = 0; j < tiket.size(); j++) {
					if (tiket.get(j).getKodeDest() == (kodeDes-1) && tiket.get(j).getKodeMask() == i) {
						if (tiket.get(j).getBayar()) {
							if (tiket.get(j).getTanggal().equals(tanggal)) {
								banyak = banyak + tiket.get(j).getBanyak();
							}
						}
					}
				}
					
				if (banyak <20) {
					temp = Integer.toString(20 - banyak) + "x";
				} else {
					temp = "Tiket Habis";
				}
			} 
			
			for(int j = temp.length(); j <=13; j++) {
				space = space + " ";
			}
			String tiketTersedia = space + temp + " |"; //CETAK TERSEDIA 
			
			System.out.println("| [" + (i+1) + "] | " + nama + jamKeberangkatan + harga + tiketTersedia); //CETAK SATU KOLOM
		}
		System.out.println("+-----+-------------------+-------------------+----------------+----------------+");
		System.out.println("[88] SALAH INPUT BANYAK TIKET?");
		System.out.println("[99] BATAL");
		System.out.print("\nPILIH : ");
	}
	
	@Override
	public void cetak() {
		System.out.println("+-----+-------------------+-------------------+----------------+");
		System.out.println("|     |     Maskapai      | Jam Keberangkatan |    Harga/jam   |");
		System.out.println("+-----+-------------------+-------------------+----------------+");
		
		for (byte i = 0; i < mask.size();i++) {
			DecimalFormat df = new DecimalFormat("###,###");
			
			//
			String space = "";
			String temp = mask.get(i).nama;
			for(int j = temp.length(); j <= 16; j++) {
				space = space + " ";
			}
			String nama = temp + space + " | "; // CETAK NAMA
			
			//
			space = "";
			temp = mask.get(i).jamKeberangkatan + " WITA";
			for(int j = temp.length(); j <=16; j++) {
				space = space + " ";
			}
			String jamKeberangkatan = temp + space + " | "; //CETAK JAM KEBERANGKATAN
			
			//
			space = "";
			double hrg = mask.get(i).harga;
			temp = "Rp. " + df.format(hrg);
			for(int j = temp.length(); j <=13; j++) {
				space = space + " ";
			}
			String harga = temp + space + " | "; //CETAK HARGA
			
			System.out.println("| [" + (i+1) + "] | " + nama + jamKeberangkatan + harga ); //CETAK SATU KOLOM
		}
	}

}

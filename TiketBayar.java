package FlightReservation;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TiketBayar extends DataPenerbangan implements CariTiket{

	private String tanggal;
	private int id, kodeDest, kodeMask;
	private byte banyak;
	private boolean bayar;

	public byte getBanyak() {
		return banyak;
	}
	public boolean getBayar() {
		return bayar;
	}
	public boolean getBayar(int i) {
		return tiketB.get(i).bayar;
	}
	public int getKodeDest() {
		return kodeDest;
	}
	public int getKodeMask() {
		return kodeMask;
	}
	public String getTanggal() {
		return tanggal;
	}
	public String getId(int i) {
		return "VMD-" + String.format("%04d", tiketB.get(i-1).id);
	}
	public int getKodeDest(int i) {
		return tiketB.get(i).kodeDest;
	}
	public int getKodeMask(int i) {
		return tiketB.get(i).kodeMask;
	}
	public byte getBanyak(int i) {
		return tiketB.get(i).banyak;
	}
	public String getTanggal(int i) {
		return tiketB.get(i).tanggal;
	}
	
	public TiketBayar(int id, int kodeDest, int kodeMask, String tanggal, byte banyak, boolean bayar) {
		this.id = id;
		this.kodeDest = kodeDest;
		this.kodeMask = kodeMask;
		this.tanggal = tanggal;
		this.banyak = banyak;
		this.bayar = bayar;
	}
	
	public int banyakArray() {
		return tiketB.size();
	}
	
	ArrayList<TiketBayar> tiketB = new ArrayList<TiketBayar>();
	public ArrayList<TiketBayar> getArrayList() {
		return tiketB;
	}

	
	public void tambah(byte kodeDest, byte kodeMask, String tgl, byte banyak) {
		int idTiket = 1;			
		
		if (tiketB.size() != 0) {
			int lastId = tiketB.get(tiketB.size()-1).id;
			idTiket = lastId + 1;
		}
		tiketB.add(new TiketBayar(idTiket, kodeDest-1, kodeMask-1, tgl, banyak, false));
	}
	
	
	public int getBanyakTiket(byte kodeDest, byte kodeMask, String tanggal) { // BANYAK TIKET YANG SUDAH DIBAYAR
		int banyak = 0;
		for (byte j = 0; j < tiketB.size(); j++) {
			if (tiketB.get(j).getKodeDest() == (kodeDest-1) && tiketB.get(j).getKodeMask() == (kodeMask-1)) {
				if (tiketB.get(j).bayar) {
					if (tiketB.get(j).tanggal.equals(tanggal)) {
						banyak = banyak + tiketB.get(j).getBanyak();
					}
				}
			}
		}
		return banyak;
	}

	public void cetak(int i, ArrayList<Destinasi> des, ArrayList<Maskapai> mas) {
		int kodeMask = tiketB.get(i).kodeMask;
		
		int kodeDest = tiketB.get(i).kodeDest;
		
		System.out.println("\n+-------------------------------------------+");
		System.out.println("|             TIKET PEMBAYARAN              |");
		System.out.println("+-------------------------------------------+");
		System.out.println("|                KODE BAYAR                 |");
		
		int id = tiketB.get(i).id;
		String idTiket = String.format("%04d", id);
		
		System.out.println("|                 VMD-"+ idTiket +"                  |");
		System.out.println("+-------------------------------------------+");

		String destinasi = des.get(kodeDest).getAsal() + " - " + des.get(kodeDest).getTujuan();
		String space = "";
		for(int j = destinasi.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Destinasi         : "+ destinasi + space + "|");
		
		String maskapai = mas.get(kodeMask).getNama();
		space = "";
		for(int j = maskapai.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Maskapai          : "+ maskapai + space + "|");
		
		String jamKeberangkatan = mas.get(kodeMask).getJamKeberangkatan();
		space = "";
		for(int j = jamKeberangkatan.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Jam Keberangkatan : "+ jamKeberangkatan + space + "|");

		String tanggal = tiketB.get(i).tanggal;
		space = "";
		for(int j = tanggal.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Tanggal           : "+ tanggal + space + "|");
		
		double harga = mas.get(kodeMask).getHarga() * des.get(kodeDest).jamPerjalanan;
		DecimalFormat df = new DecimalFormat("###,###");
		String hrg = "Rp. " + df.format(harga);
		space = "";
		for(int j = hrg.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Harga             : "+ hrg + space + "|");
		
		byte banyak = tiketB.get(i).banyak;
		space="";
		String byk = Byte.toString(banyak);
		for(int j = byk.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Jumlah            : "+ byk + space + "|");

		double total = mas.get(kodeMask).getHarga() * des.get(kodeDest).jamPerjalanan * tiketB.get(i).banyak;
		String tot = "Rp. " + df.format(total);
		space = "";
		for(int j = tot.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Total             : "+ tot + space + "|");
		
		String status = "Belum Dibayar";		
		if (tiketB.get(i).bayar) {
			status = "Sudah Dibayar";
		}
		space = "";
		for(int j = status.length(); j < 22; j++) {
			space = space + " ";
		}
		System.out.println("| Status            : "+ status + space + "|");
		

	
		System.out.println("+-------------------------------------------+");
	}
	
	public void cetak() {
		System.out.println("[1] Selesai");
		System.out.println("[2] Bayar Tiket");
		System.out.print("\nPILIH : ");
		
	}
	
	public int cariTiket(String kode) {
		for (int i = 0; i < tiketB.size(); i++ ) {
			String idTiket = getId(i+1);
			if(idTiket.equals(kode)) {
				return i;
			}
		}
		return -1;
	}
	
	public void bayarTiket(int i, ArrayList<Maskapai> mask, ArrayList<Destinasi> dest, double bayar) {
		int kodeMask = tiketB.get(i).kodeMask;
		int kodeDest = tiketB.get(i).kodeDest;
		double jamP = dest.get(kodeDest).jamPerjalanan;
		double harga = mask.get(kodeMask).getHarga() * jamP * tiketB.get(i).banyak;
		if (bayar >= harga) {
			
			int id = tiketB.get(i).id;
			byte banyak = tiketB.get(i).banyak;
			String tanggal = tiketB.get(i).tanggal;
			tiketB.set(i, new TiketBayar(id, kodeDest, kodeMask, tanggal, banyak, true));
			
			DecimalFormat df = new DecimalFormat("###,###");
			System.out.println("+--------------------------+");
			System.out.println("|  PEMBAYARAN BERHASIL! ^^ |");
			System.out.println("+--------------------------+");
			System.out.println("KEMBALIAN ANDA : Rp."+ df.format(bayar - harga));
			
		} else if (bayar == 88){
			System.out.println("\n[88] KEMBALI");
		} else if (bayar < harga){				
			System.out.println("\nMAAF UANGNYA KURANG KAK :)");
		}
		
	}
	
	
	public void hapus(int i) {
		tiketB.remove(i);
		System.out.println("\n+------------------------------+");
		System.out.println("| TIKET BERHASIL DIBATALKAN ^^ |");
		System.out.println("+------------------------------+");
	}
	
}

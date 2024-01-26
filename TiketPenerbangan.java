package FlightReservation;

import java.util.ArrayList;

public class TiketPenerbangan extends DataPenerbangan implements CariTiket{
	
	private int id, kodeDest, kodeMask;
	private String tanggal, tempatDuduk;
	
	public String getId(int i) {
		return "TIC-" + String.format("%04d", tiketP.get(i).id);
	}
	
	public TiketPenerbangan(int id, int kodeDest, int kodeMask, String tanggal, String tempatDuduk) {
		this.id = id;
		this.kodeDest = kodeDest;
		this.kodeMask = kodeMask;
		this.tanggal = tanggal;
		this.tempatDuduk = tempatDuduk;
	}
	
	public int banyakArray() {
		return tiketP.size();
	}
	
	ArrayList<TiketPenerbangan> tiketP = new ArrayList<TiketPenerbangan>();
	
	public ArrayList<TiketPenerbangan> getArrayList() {
		return tiketP;
	}

	public void tambah(int kodeDest, int kodeMask, String tanggal) {
		int idTiket = 1;			
		String tempatDuduk = "A1";
		
		if (tiketP.size() > 0) {
			int lastId = tiketP.get(tiketP.size()-1).id;
			idTiket = lastId + 1;
			
			for(int i = 0; i < tiketP.size(); i++) {
				int kdM = tiketP.get(i).kodeMask;
				int kdD = tiketP.get(i).kodeDest;
				if (kdM == kodeMask && kdD == kodeDest) {
					if(tanggal.equals(tiketP.get(i).tanggal)) {
						tempatDuduk = tiketP.get(i).tempatDuduk;
						char c = tempatDuduk.charAt(0);
						char k = tempatDuduk.charAt(1);
						k++;
						if(k == '9') {
							k = '1';
							c++;
						}
						tempatDuduk = Character.toString(c) + Character.toString(k);
					}
				}
			}
		}
		
		tiketP.add(new TiketPenerbangan(idTiket, kodeDest, kodeMask, tanggal, tempatDuduk));			
		
	}
	
	public void cetak(ArrayList<Destinasi> des, ArrayList<Maskapai> mas) {
		int i = tiketP.size()-1;
		int kodeDest = tiketP.get(i).kodeDest;
		int kodeMas = tiketP.get(i).kodeMask;
			
		System.out.println("+-------------------------------------------------------------+");
		System.out.println("|                     TIKET PENERBANGAN                       |");
		System.out.println("+-------------------------------------------------------------+");
		System.out.println("|                         KODE TIKET                          |");
		
		int id = tiketP.get(i).id;
		String idTiket = String.format("%04d", id);
		
		System.out.println("|                          TIC-" + idTiket + "                           |");
		System.out.println("+-------------------------------------------------------------+");

		String asal = des.get(kodeDest).getAsal();
		String space1 = "";
		for(int j = asal.length(); j < 31; j++) {
			space1 = space1 + " ";
		}
		
		String jamKeberangkatan = mas.get(kodeMas).getJamKeberangkatan();
		String space2 = "";
		for(int j = jamKeberangkatan.length(); j < 28; j++) {
			space2 = space2+ " ";
		}
		System.out.println("| Dari                           Jam Keberangkatan            |");
		System.out.println("| " + asal + space1 + jamKeberangkatan + space2 + " |");
		System.out.println("|                                                             |");
		
		String tujuan = des.get(kodeDest).getTujuan();
		space1 = "";
			for(int j = tujuan.length(); j < 31; j++) {
				space1 = space1 + " ";
			}
		
		String maskapai = mas.get(kodeMas).getNama();
		space2 = "";
		for(int j = maskapai.length(); j < 28; j++) {
			space2 = space2 + " ";
		}
		System.out.println("| Tujuan                         Maskapai                     |");
		System.out.println("| " + tujuan + space1 + maskapai + space2 + " |");
		System.out.println("|                                                             |");
		
		//TANGGAL
		String tanggal = tiketP.get(i).tanggal;
		space1 = "";
		for(int j = tanggal.length(); j < 31; j++) {
			space1 = space1 + " ";
		}
			
		//TEMPAT DUDUK
		String tempatDuduk = tiketP.get(i).tempatDuduk;
		space2 = "";
		for(int j = tempatDuduk.length(); j < 28; j++) {
			space2 = space2 + " ";
		}
		
		System.out.println("| Tanggal                        Tempat Duduk                 |");
		System.out.println("| " + tanggal + space1 + tempatDuduk + space2 + " |");
		System.out.println("|                                                             |");
		
		System.out.println("| (Diharapkan Tiba 30 Menit Sebelum Jam Keberangakatan ^^)    |");
		System.out.println("+-------------------------------------------------------------+");
	
	}
	
	public int cariTiket(String kode) {
		for (int i = 0; i < tiketP.size(); i++ ) {
			String idTiket = getId(i);
			if(idTiket.equals(kode)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	void cetak() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void hapus(int index) {
		// TODO Auto-generated method stub
		
	}
	
	
}

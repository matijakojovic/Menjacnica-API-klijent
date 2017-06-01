package rs.ac.bg.fon.ai.dodatna.kojovic.matija.domain;

import java.util.Date;

public class Konverzija {
	Date datum;
	String iz;
	String u;
	double kurs;
	
	public Konverzija(String iz, String u, double kurs) {
		super();
		this.datum = new Date();
		this.iz = iz;
		this.u = u;
		this.kurs = kurs;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getIz() {
		return iz;
	}

	public void setIz(String iz) {
		this.iz = iz;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public double getKurs() {
		return kurs;
	}

	public void setKurs(double kurs) {
		this.kurs = kurs;
	}
	
}
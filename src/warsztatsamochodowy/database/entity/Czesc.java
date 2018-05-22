/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

/**
 *
 * @author Sc00rpyY-1
 */
public class Czesc {
    
    
        private String ID,Nazwa, Producent, Ilosc, Cena;

        public Czesc(String id,String nazwa, String producent, String ilosc, String cena) {
            this.ID = id;
            this.Nazwa = nazwa;
            this.Producent = producent;
            this.Ilosc = ilosc;
            this.Cena = cena;
        }

        public String getNazwa() {
            return Nazwa;
        }

        public String getProducent() {
            return Producent;
        }

        public String getIlosc() {
            return Ilosc;
        }

        public String getCena() {
            return Cena;
        }
                public String getID() {
            return ID;
        }
    
}

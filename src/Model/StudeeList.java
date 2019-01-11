/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Keeprawteach
 */
public class StudeeList {
    
        private final SimpleStringProperty Bn;
        private final SimpleStringProperty Bt;
        private final SimpleStringProperty Ba;
        private final SimpleStringProperty Bp;
        

        public StudeeList(String Bn, String Bt, String Ba, String Bp) {
            this.Bn = new SimpleStringProperty(Bn);
            this.Bt = new SimpleStringProperty(Bt);
            this.Ba = new SimpleStringProperty(Ba);
            this.Bp = new SimpleStringProperty(Bp);
           
        }

        public String getBn() {
            return Bn.get();
        }

        public String getBt() {
            return Bt.get();
        }

        public String getBa() {
            return Ba.get();
        }

        public String getBp() {
            return Bp.get();
        }

}

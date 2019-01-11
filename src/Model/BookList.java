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
public class BookList {

        private final SimpleStringProperty Bn;
        private final SimpleStringProperty Bt;
        private final SimpleStringProperty Ba;
        private final SimpleStringProperty Bp;
        private final SimpleStringProperty St;

        public BookList(String Bn, String Bt, String Ba, String Bp, String St) {
            this.Bn = new SimpleStringProperty(Bn);
            this.Bt = new SimpleStringProperty(Bt);
            this.Ba = new SimpleStringProperty(Ba);
            this.Bp = new SimpleStringProperty(Bp);
            this.St = new SimpleStringProperty(St);
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

        public String getSt() {
            return St.get();
        }
        

    }

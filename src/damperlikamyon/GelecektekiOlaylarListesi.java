/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damperlikamyon;

import java.util.LinkedList;

/**
 *
 * @author Furkan
 */
public class GelecektekiOlaylarListesi extends LinkedList<Olay>{
    
    public GelecektekiOlaylarListesi() {
        super();
    } 
    public void sırayaSok(Olay olay) {
        add(olay);
    }
    
    public void sıradanÇıkar() {
        removeFirst();
    }
}

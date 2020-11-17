/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damperlikamyon;

import java.util.Random;

/**
 *
 * @author Furkan
 */
public class Olay {

    private int süre;
    private Kamyon kamyon;
    private String olayTipi;

    public Olay(String olayTipi, Random r, Kamyon kamyon) {
        this.olayTipi = olayTipi;
        int sayı = r.nextInt(10);
        this.kamyon = kamyon;
        switch (olayTipi) {
            case "Yukleme_Bitis": {
                if (sayı < 3) {
                    süre = 5;
                    break;
                }
                if (sayı < 8) {
                    süre = 10;
                    break;
                }
                if (sayı < 10) {
                    süre = 15;
                    break;
                }
            }
            case "Tartim_Bitis": {
                if (sayı < 7) {
                    süre = 12;
                    break;
                }
                if (sayı < 10) {
                    süre = 16;
                    break;
                }
            }
            case "Yuklemeye_Varis": {
                if (sayı < 4) {
                    süre = 40;
                    break;
                }
                if (sayı < 7) {
                    süre = 60;
                    break;
                }
                if (sayı < 9) {
                    süre = 80;
                    break;
                }
                if (sayı < 10) {
                    süre = 100;
                    break;
                }
            }
            default: {
                break;
            }
        }
    }

    public String olayTipi() {
        return olayTipi;
    }

    public double süre() {
        return süre;
    }

    public double olayıGerçekleştir(double zaman) {
        return zaman + this.süre;
    }
}

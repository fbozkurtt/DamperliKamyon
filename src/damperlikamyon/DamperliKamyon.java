/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package damperlikamyon;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Furkan
 */
public class DamperliKamyon {

    public static Random rastgele;
    public static int zaman;
    public static int yükleyiciMeşgulSüresi;
    public static int tartıMeşgulSüresi;
    public static ArrayList<Kamyon> kamyonlar;
    public static ArrayList<Kamyon> yükleyiciSırası;
    public static ArrayList<Kamyon> tartımSırası;
    public static GelecektekiOlaylarListesi gelecektekiOlaylarListesi;
    public static int yüklemedekiKamyonSayısı;
    public static int tartımdakiKamyonSayısı;

    /**
     * @param args the command line arguments
     */
    public static void başlat() {
        rastgele = new Random();
        zaman = 0;
        yükleyiciMeşgulSüresi = 0;
        tartıMeşgulSüresi = 0;
        kamyonlar = new ArrayList<>();
        yükleyiciSırası = new ArrayList<>();
        tartımSırası = new ArrayList<>();
        gelecektekiOlaylarListesi = new GelecektekiOlaylarListesi();
        for (int i = 1; i <= 6; i++) {
            kamyonlar.add(new Kamyon("DT" + i));
        }
        yükleyiciSırası.add(kamyonlar.get(3));
        yükleyiciSırası.add(kamyonlar.get(4));
        yükleyiciSırası.add(kamyonlar.get(5));
        yüklemedekiKamyonSayısı = yükleyiciSırası.size();
        tartımdakiKamyonSayısı = tartımSırası.size();
        gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, kamyonlar.get(1)));
        gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, kamyonlar.get(2)));
        gelecektekiOlaylarListesi.add(new Olay("Tartim_Bitis", rastgele, kamyonlar.get(0)));
    }

    public static void main(String[] args) {
        başlat();
        while (true) {
            zaman += minimumSüreliOlay(gelecektekiOlaylarListesi).olayıGerçekleştir(zaman);
        }
    }

    public static Olay minimumSüreliOlay(GelecektekiOlaylarListesi gol) {
        Olay minOlay = gol.getFirst();
        for (int i = 0; i < gol.size(); i++) {
            if(gol.get(i).süre()<minOlay.süre()){
                minOlay = gol.get(i);
            }
        }
        return minOlay;
    }
}

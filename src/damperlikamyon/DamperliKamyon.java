package damperlikamyon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Furkan Bozkurt @numara y195012058
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
        yükleyiciSırası.add(kamyonlar.get(3));//DT4
        yükleyiciSırası.add(kamyonlar.get(4));//DT5
        yükleyiciSırası.add(kamyonlar.get(5));//DT6
        yüklemedekiKamyonSayısı = 2;
        tartımdakiKamyonSayısı = 1;
        gelecektekiOlaylarListesi.add(new Olay("Tartim_Bitis", rastgele, kamyonlar.get(0)));//DT1
        gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, kamyonlar.get(1)));//DT2
        gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, kamyonlar.get(2)));//DT3
    }

    public static void main(String[] args) {
        başlat();
        int simülasyonSüresi = 0;
        Scanner girdi = new Scanner(System.in);
        System.out.print("Simülasyon kaç birim zaman çalışsın?\n>:");
        simülasyonSüresi = girdi.nextInt();
        System.out.println("ZAMAN | KAMYON | YÜKLEYİCİ MEŞGUL S. | TARTI MEŞGUL S. | OLAY");
        while (zaman <= simülasyonSüresi) {
            Olay gerçekleştirilecekOlay = minimumSüreliOlay(gelecektekiOlaylarListesi);

            if (zaman + gerçekleştirilecekOlay.süre() >= simülasyonSüresi) {
                break;
            }
            gelecektekiOlaylarListesi.remove(gerçekleştirilecekOlay);
            if (yüklemedekiKamyonSayısı > 0) {
                yükleyiciMeşgulSüresi += gerçekleştirilecekOlay.süre() * yüklemedekiKamyonSayısı;
            }
            if (tartımdakiKamyonSayısı > 0) {
                tartıMeşgulSüresi += gerçekleştirilecekOlay.süre();
            }
            for (int i = 0; i < gelecektekiOlaylarListesi.size(); i++) {
                gelecektekiOlaylarListesi.get(i).süreyiAzalt(gerçekleştirilecekOlay.süre());
            }
            if ("Tartim_Bitis".equals(gerçekleştirilecekOlay.olayTipi())) {
                tartımdakiKamyonSayısı = 0;
                gelecektekiOlaylarListesi.add(new Olay("Yuklemeye_Varis", rastgele, gerçekleştirilecekOlay.kamyon()));
                if (tartımSırası.size() > 0) {
                    tartımdakiKamyonSayısı = 1;
                    Kamyon tartımSırasıÖndekiKamyon = tartımSırası.get(0);
                    gelecektekiOlaylarListesi.add(new Olay("Tartim_Bitis", rastgele, tartımSırasıÖndekiKamyon));
                    tartımSırası.remove(tartımSırasıÖndekiKamyon);
                }
            }
            if ("Yukleme_Bitis".equals(gerçekleştirilecekOlay.olayTipi())) {
                yüklemedekiKamyonSayısı--;
                if (tartımdakiKamyonSayısı == 0) {
                    tartımdakiKamyonSayısı = 1;
                    gelecektekiOlaylarListesi.add(new Olay("Tartim_Bitis", rastgele, gerçekleştirilecekOlay.kamyon()));
                } else {
                    tartımSırası.add(gerçekleştirilecekOlay.kamyon());
                }
                if (yüklemedekiKamyonSayısı < 2) {
                    if (yükleyiciSırası.size() > 0) {
                        yüklemedekiKamyonSayısı++;
                        Kamyon yüklemeSırasıÖndekiKamyon = yükleyiciSırası.get(0);
                        gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, yüklemeSırasıÖndekiKamyon));
                        yükleyiciSırası.remove(yüklemeSırasıÖndekiKamyon);
                    }
                }
            }
            if ("Yuklemeye_Varis".equals(gerçekleştirilecekOlay.olayTipi())) {
                if (yüklemedekiKamyonSayısı < 2) {
                    yüklemedekiKamyonSayısı++;
                    gelecektekiOlaylarListesi.add(new Olay("Yukleme_Bitis", rastgele, gerçekleştirilecekOlay.kamyon()));
                } else {
                    yükleyiciSırası.add(gerçekleştirilecekOlay.kamyon());
                }
            }
            zaman += gerçekleştirilecekOlay.süre();
            System.out.println(zaman + "       " + gerçekleştirilecekOlay.kamyon().Name() + "               " + yükleyiciMeşgulSüresi + "                  " + tartıMeşgulSüresi + "         " + gerçekleştirilecekOlay.olayTipi());
        }
        System.out.println("Yükleyicinin meşgul süresi: " + yükleyiciMeşgulSüresi + "\nTartının meşgul süresi " + tartıMeşgulSüresi);
    }

    public static Olay minimumSüreliOlay(GelecektekiOlaylarListesi gol) {
        Olay minOlay = gol.getFirst();
        for (int i = 0; i < gol.size(); i++) {
            if (gol.get(i).süre() < minOlay.süre()) {
                minOlay = gol.get(i);
            }
        }
        return minOlay;
    }
}

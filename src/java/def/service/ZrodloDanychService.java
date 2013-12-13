package def.service;

import def.pojo.Kolumna;
import def.pojo.Tabela;
import java.util.List;

public interface ZrodloDanychService {
    public List<Kolumna> getKolumnyTabeli(String tabela);
    public List<Tabela> getTabele();
}

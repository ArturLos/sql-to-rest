package def.service;

import def.pojo.Kolumna;
import def.pojo.Struktura;
import def.pojo.Tabela;
import java.util.List;
import java.util.Set;

public interface StrukturaService {
    public List<Struktura> getAll();    
    public void add(Struktura s);
    public void remove(String nazwa);
    public void add(Tabela t);
    public void remove(Tabela t);
    public void updateKolumny(Tabela t, Set<Kolumna> set);
    public Struktura getStruktura(String idStruktura);
}

package def.pojo;

import java.util.LinkedHashSet;
import java.util.Set;

public class Tabela {
    
    private Integer id;
    private String nazwa;
    private Struktura struktura;
    private Set<Kolumna> kolumny;

    public Tabela() {
    }

    public Tabela(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Struktura getStruktura() {
        return struktura;
    }

    public void setStruktura(Struktura struktura) {
        this.struktura = struktura;
    }

    public Set<Kolumna> getKolumny() {
        if(kolumny != null){
            return kolumny;
        }else{
            kolumny=new LinkedHashSet<Kolumna>();
            return kolumny;
        }
    }

    public void setKolumny(Set<Kolumna> kolumny) {
        this.kolumny = kolumny;
    }

    @Override
    public String toString() {
        return "Tabela{" + "id=" + id + ", nazwa=" + nazwa + '}';
    }
    
}

package def.pojo;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.validator.constraints.Length;

public class Struktura {
    private int id;
    @Length(min = 3,max = 29, message = "Nazwa biblioteki musi mieć długość między 3 a 29")
    private String nazwa;
    private Set<Tabela> tabele;

    public Struktura() {
    }

    public Struktura(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int ID) {
        this.id = ID;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public String getNazwaKolekcji() {
        //TODO: Zrobić prawdziwą implementację
        return nazwa+"Kolekcja";
    }

    public void setNazwaKolekcji(String nazwa) {
        //TODO: Zrobić prawdziwą implementację
    }
    
    public String getNazwaElementu() {
        //TODO: Zrobić prawdziwą implementację
        return nazwa;
    }

    public void setNazwaElementu(String nazwa) {
        //TODO: Zrobić prawdziwą implementację
    }

    /**
     * Jeżeli kolekcja jest pusta zwraca Empty Immutable List.
     * Nie można do niej dodawać elementów!!!
     * @return 
     */
    public Set<Tabela> getTabele() {
        if(tabele!=null)
            return tabele;
        else
            return Collections.emptySet();
    }

    public void setTabele(Set<Tabela> tabele) {
        this.tabele = tabele;
    }
    
    public Tabela getTabela(String idTabela){
        Iterator<Tabela> set = getTabele().iterator();
        Tabela el;
        while(set.hasNext()){
            el = set.next();
            if(el.getNazwa().equals(idTabela))
                return el;
        }
        return null;
    }
    
}

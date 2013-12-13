package def.pojo;

import java.util.HashSet;

public class Kolumna {
    private Integer id;
    private String nazwa;
    private Boolean primary;
    private Tabela tabela;

    public Kolumna() {
    }

    public Kolumna(String nazwa) {
        this.nazwa = nazwa;
    }

    public Kolumna(String nazwa, Boolean primary) {
        this.nazwa = nazwa;
        this.primary = primary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    
    public String getNazwa(){
        return nazwa;
    }
    public Boolean isPrimary(){
        return primary;
    }

    public Tabela getTabela() {
        return tabela;
    }

    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    /* Obydwie metody musiały być zaimplementowane w celu automatycznego bindowania
     pól w formularzu. Pola dzięki temu są zaznaczane zgodnie z modelem. */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + (this.nazwa != null ? this.nazwa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kolumna other = (Kolumna) obj;
        if ((this.nazwa == null) ? (other.nazwa != null) : !this.nazwa.equals(other.nazwa)) {
            return false;
        }
        return true;
    }
    
    
}

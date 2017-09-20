package com.example.saba.faculdadev1;

/**
 * Created by saba on 28/08/15.
 */
public class DisciplinaValue implements java.io.Serializable {

    private Long idTipoExame;
    private String tipo;

    public Long getIdTipoExame() {
        return idTipoExame;
    }

    public void setIdTipoExame(Long id) {
        this.idTipoExame = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String toString(){
        return this.getTipo();
    }


}

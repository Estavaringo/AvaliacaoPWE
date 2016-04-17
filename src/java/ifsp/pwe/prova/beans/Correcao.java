/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.pwe.prova.beans;

/**
 *
 * @author Flávio e Gabriel
 */
public class Correcao {
    
    private int id;
    private String comentario;
    private int idUsuario;
    private int idAtividade;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idAtividade
     */
    public int getIdAtividade() {
        return idAtividade;
    }

    /**
     * @param idAtividade the idAtividade to set
     */
    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }
}

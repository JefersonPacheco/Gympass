/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Dominio;

public class ResultadoCorrida {
    
    private String Hora;
    private String CodPiloto;
    private String Piloto;
    private String NumVolta;
    private String TempoVolta;
    private String VelocidadeMedia;
    
    private int Colocacao;

    public int getColocacao() {
        return Colocacao;
    }

    public void setColocacao(int Colocacao) {
        this.Colocacao = Colocacao;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public String getCodPiloto() {
        return CodPiloto;
    }

    public void setCodPiloto(String CodPiloto) {
        this.CodPiloto = CodPiloto;
    }

    public String getPiloto() {
        return Piloto;
    }

    public void setPiloto(String Piloto) {
        this.Piloto = Piloto;
    }

    public String getNumVolta() {
        return NumVolta;
    }

    public void setNumVolta(String NumVolta) {
        this.NumVolta = NumVolta;
    }

    public String getTempoVolta() {
        return TempoVolta;
    }

    public void setTempoVolta(String TempoVolta) {
        this.TempoVolta = TempoVolta;
    }

    public String getVelocidadeMedia() {
        return VelocidadeMedia;
    }

    public void setVelocidadeMedia(String VelocidadeMedia) {
        this.VelocidadeMedia = VelocidadeMedia;
    }

}

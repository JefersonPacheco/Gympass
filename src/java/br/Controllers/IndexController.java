/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.Controllers;

import br.Dominio.ResultadoCorrida;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Jeferson Pacheco (Susulo)
 */

@Controller
public class IndexController {
    
    final String sucesso = "alert alert-success";
    final String falha = "alert alert-danger";
          
    @RequestMapping("/")
    public String iniciar(){
        return "index";
    }
        
    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, Model model) throws IOException{
        
        // Declaração da Variáveis que serão utilizadas
        String content = "";
        List<ResultadoCorrida> listaCorrida = new ArrayList<>();
        List<ResultadoCorrida> listaVencedores = new ArrayList<>();
        int contColocacao = 1;
        
        // Validar se o arquivo enviado é um .txt
        if(!file.getOriginalFilename().endsWith(".txt")){
            content = "Favor, anexar um arquivo .txt";
            model.addAttribute("mensagem", falha);
            model.addAttribute("mensagem2", content);
            return "index";
        }
        
        // Transformar o arquivo lido em String
        content = new String(file.getBytes(), "UTF-8");
        
        // Limpar o Arquivo até obter todos os dados separados por ;
        content = LimparArquivo(content);
        
        // Separar os dados lidos neste vetor
        String split[] = content.split(";");
        
        // Popular a classe ResultadoCorrida com os dados lidos, inicia em 10 para desconsiderar o cabeçalho
        listaCorrida = leituraArquivoSplit(split, listaCorrida);
        
        // Melhor volta da Corrida
        ResultadoCorrida melhorVolta = listaCorrida.get(0);
        for(int i = 0; i < listaCorrida.size() -1; i++)
        {
            if (melhorVolta.getTempoVolta().compareTo(listaCorrida.get(i + 1).getTempoVolta()) > 0)
                melhorVolta = listaCorrida.get(i + 1);             
        }
        
        // Criar a lista de chegada
        for(ResultadoCorrida resultado : listaCorrida)
        {
            if (resultado.getNumVolta().equals("1")) {
                resultado.setColocacao(contColocacao);                
                resultado.setTempoVolta(calculaTempoTotalPiloto(resultado, listaCorrida));
                listaVencedores.add(resultado);                 
            }
        }
               
        // BubbleSort para ordenar a ordem de chegada
        for(int i = 0; i < listaVencedores.size(); i++){
            for(int j = 0; j < listaVencedores.size() - 1; j++){
                if(listaVencedores.get(j).getTempoVolta().compareTo(listaVencedores.get(j + 1).getTempoVolta()) > 0){
                    ResultadoCorrida aux = listaVencedores.get(j);
                    listaVencedores.set(j, listaVencedores.get(j+1));
                    listaVencedores.set(j+1, aux);
                }
            }
        }
        
        // Setar a colocação do piloto na corrida
        for(int i = 1; i <= listaVencedores.size(); i++)
            listaVencedores.get(i - 1).setColocacao(i);
        
        // Setar a qtde de voltas completas
        for(int i = 0; i < listaVencedores.size(); i++)
        {
            for(int j = 0; j < listaCorrida.size(); j++)
            {
                if(listaVencedores.get(i).getCodPiloto().equals(listaCorrida.get(j).getCodPiloto()))
                    listaVencedores.get(i).setNumVolta(listaCorrida.get(j).getNumVolta());
            }
                
        }

        model.addAttribute("listaVencedores", listaVencedores);
        model.addAttribute("melhorVolta", melhorVolta);
        
        return "index";
    }
    
    private String calculaTempoTotalPiloto(ResultadoCorrida resultado, List<ResultadoCorrida> listaCorrida){
        
        Duration total = Duration.ZERO;

        for(ResultadoCorrida r : listaCorrida)
        {
            if(resultado.getCodPiloto().equals(r.getCodPiloto())){
                String tempo[] = r.getTempoVolta().replace(".", ":").split(":");

                total = total.plusMinutes(Long.parseLong(tempo[0]))
                             .plusSeconds(Long.parseLong(tempo[1]))
                             .plusMillis(Long.parseLong(tempo[2]));
            }
        }

        long totalSegundos = total.getSeconds();
        long totalHoras = totalSegundos / 3600;
        totalSegundos -= (totalHoras * 3600);
        long totalMilis = (totalSegundos % 1000);
        long totalMinutos = totalSegundos / 60;
        totalSegundos -= (totalMinutos * 60);
        return String.format("%02d:%02d:%02d.%02d", totalHoras, totalMinutos, totalSegundos, totalMilis);
    }
    
    private String LimparArquivo(String content){
         for(int i = 0; i <= 10; i++){
            content = content.replace("\t", " ");
            content = content.replace("  ", " ");
            content = content.replace("�", "");
        }
        content = content.replace(" – ", " ");
        content = content.replace(" ", ";");
        content = content.replace("\r\n", ";");
        content = content.replace("\t", "");
        content = content.replace("\t\t", ";");
        return content;
    }

    private List<ResultadoCorrida> leituraArquivoSplit(String split[], List<ResultadoCorrida> listaCorrida) {
        for(int i = 10; i < split.length;){
            ResultadoCorrida resultado = new ResultadoCorrida();
            resultado.setHora(split[i]);
            resultado.setCodPiloto(split[i + 1]);
            resultado.setPiloto(split[i + 2]);
            resultado.setNumVolta(split[i + 3]);
            resultado.setTempoVolta(split[i + 4]);
            resultado.setVelocidadeMedia(split[i + 5]);
            listaCorrida.add(resultado);
            i += 6;
        }
        return listaCorrida;
    }
}

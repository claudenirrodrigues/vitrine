/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitrine.entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Familia
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.vitrine.entities.service.AcabamentoService.class);
        resources.add(com.vitrine.entities.service.CarrinhoService.class);
        resources.add(com.vitrine.entities.service.CategoriaService.class);
        resources.add(com.vitrine.entities.service.CorService.class);
        resources.add(com.vitrine.entities.service.DetalheService.class);
        resources.add(com.vitrine.entities.service.EnderecoService.class);
        resources.add(com.vitrine.entities.service.EstoqueService.class);
        resources.add(com.vitrine.entities.service.FaixaCEPService.class);
        resources.add(com.vitrine.entities.service.FreteService.class);
        resources.add(com.vitrine.entities.service.NumeracaoService.class);
        resources.add(com.vitrine.entities.service.PagamentoService.class);
        resources.add(com.vitrine.entities.service.PedidoCompraService.class);
        resources.add(com.vitrine.entities.service.PercentualVendaService.class);
        resources.add(com.vitrine.entities.service.PerfilService.class);
        resources.add(com.vitrine.entities.service.PessoaService.class);
        resources.add(com.vitrine.entities.service.PessoaFaixaCEPService.class);
        resources.add(com.vitrine.entities.service.ProdutoService.class);
        resources.add(com.vitrine.entities.service.TamanhoService.class);
        resources.add(com.vitrine.entities.service.TipoProdutoService.class);
        resources.add(com.vitrine.entities.service.UsuarioService.class);
        resources.add(com.vitrine.entities.service.security.LoginService.class);
        resources.add(com.vitrine.entities.service.ResponseFilter.class);
        resources.add(com.vitrine.entities.service.RequestFilter.class);
        
        
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package professionalconnection.dao;

import javax.persistence.EntityManager;
import professionalconnection.modelo.Comunidade;

/**
 *
 * @author CaioTakei
 */
public class ComunidadeDAO {
    private EntityManager entityManager;

    /**
     * Construtor da classe DAO que chama os métodos do EntityManager.
     * 
     * @param entityManager
     */
    public ComunidadeDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    

    /**
     * Método para salvar ou atualizar
     * 
     * @param comunidade
     * @return
     * @throws java.lang.Exception
     */
    public Comunidade salvar(Comunidade comunidade) throws Exception{
        System.out.println("Salvando a comunidade: " +comunidade.getTitulo());
        /*Verifica se realmente é um registro novo*/
        if(comunidade.getId() == null){
            /*caso realmente ele seja novo, então ele salva*/
            this.entityManager.persist(comunidade);
        }else{
            /*ou então se ele ver que ja havia sido salva ele verifica se
             a entidade referente ao mesmo não esta no estado gerenciavel...*/
            if(!this.entityManager.contains(comunidade)){
                /*se a entidade não esta no estado gerenciavel, verifica se ele
                  *realmente existe na base de dados*/
                if (entityManager.find(Comunidade.class, comunidade.getId())==null){
                    throw new Exception("COMUNIDADE NÃO EXISTE");
                }
            }
             /*Faz uma atualizacao do registro que ja existia*/
            return entityManager.merge(comunidade);
        }
        /*retorna o registro que foi salvo que possamos ter o id que foi salvo*/
        return comunidade;
    }


    /**
     * Método que consulta o registro pelo ID
     *
     * @param id
     * @return
     */
    public Comunidade consultarPorId(Long id){
        return entityManager.find(Comunidade.class, id);
    }


    /**
     * Metodo que exclui o registro do banco de dados
     *
     * @param id
     */
    public void excluir(Long id){
        /* Consulta o registro na base de dados através de seu ID*/
        Comunidade comunidade = entityManager.find(Comunidade.class,id);
        System.out.println("Excluindo a comunidade:" +comunidade.getTitulo());
        
        /*Após ter encontrado na consulta, remove aqui*/
        entityManager.remove(comunidade);
    }


}



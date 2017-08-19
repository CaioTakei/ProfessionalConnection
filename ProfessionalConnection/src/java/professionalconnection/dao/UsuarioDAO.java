/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package professionalconnection.dao;

import javax.persistence.EntityManager;
import professionalconnection.modelo.Usuario;

/**
 * @author Caio Takei
 * Classe utilizada para realizar as operações com o banco de dados
 */
public class UsuarioDAO {
    private EntityManager entityManager;

    
    /**
     * Construtor da classe DAO que chama os métodos do EntityManager
     *
     * @param entityManager
     */
    public UsuarioDAO(EntityManager entityManager) {
    this.entityManager = entityManager;
    }


    /**
     *Método utilizado para salvar ou atualizar um registro no banco de dados.
     *
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
     public Usuario salvar(Usuario usuario)throws Exception{
         System.out.println("Salvando o usuario: " +usuario.getUsuario());
        /*verifica se realmente é um registro novo*/
         if(usuario.getId() == null){
             /*caso ele realmente ele seja novo, então ele salva*/
              this.entityManager.persist(usuario);
             }else{
              /*ou então se ele ver que ja havia sido salva ele verifica se 
               a entidade referente ao mesmo não esta no estado gerenciavel...*/
             if(!entityManager.contains(usuario)){
                 /*se a entidade não esta no estado gerenciavel, verifica se ele
                  *realmente existe na base de dados*/
                  if(entityManager.find(Usuario.class, usuario.getId()) == null){
                      throw new Exception("USUARIO NAO EXISTE"); 
                  }
                 }
                /*Faz uma atualizacao do registro que ja existia*/
             return entityManager.merge(usuario);
             }
         /* Retorna o registro que acabamos de salvar para que possamos
          * obter o id do mesmo.*/ 
         return usuario;
     }


     /**
      * Este método é responsável por fazer uma consulta através do id
      *
      * @param id
      * @return
      */
     public Usuario consultarPorId(Long id){
        return entityManager.find(Usuario.class,id);
     }


     /**
      * Este método busca e exclui o registro da base de dados.
      *
      * @param id
      */
     public void excluir(Long id){
         /*Primeiro consulta o registro na base de dados através do id*/
         Usuario usuario = entityManager.find(Usuario.class, id);
         System.out.println("Excluindo o usuario:"+usuario.getUsuario());
         /*Agora, após ter encontrado, exclui*/
         entityManager.remove(usuario);
     }

}




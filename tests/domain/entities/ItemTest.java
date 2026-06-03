// Testes de validação dos itens do marketplace
package domain.entities;
import domain.enums.EstadoItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ItemTest {
 @Test void naoDeveCriarLivroSemTitulo(){ Usuario u=new Usuario("Guilherme"); assertThrows(IllegalArgumentException.class,()->new Livro("",EstadoItem.USADO,u,"Autor")); }
 @Test void naoDeveCriarLivroSemAutor(){ Usuario u=new Usuario("Guilherme"); assertThrows(IllegalArgumentException.class,()->new Livro("Livro",EstadoItem.USADO,u,"")); }
 @Test void naoDeveCriarItemSemEstado(){ Usuario u=new Usuario("Guilherme"); assertThrows(IllegalArgumentException.class,()->new Livro("Livro",null,u,"Autor")); }
 @Test void naoDeveCriarItemSemProprietario(){ assertThrows(IllegalArgumentException.class,()->new Livro("Livro",EstadoItem.USADO,null,"Autor")); }
 @Test void naoDeveCriarEletronicoComGarantiaNegativa(){ Usuario u=new Usuario("Guilherme"); assertThrows(IllegalArgumentException.class,()->new Eletronico("Notebook",EstadoItem.SEMINOVO,u,-1)); }
 @Test void deveMarcarItemComoIndisponivel(){ Usuario u=new Usuario("Guilherme"); Livro l=new Livro("Dom Casmurro",EstadoItem.USADO,u,"Machado"); l.marcarIndisponivel(); assertFalse(l.isDisponivel()); }
 @Test void naoDeveMarcarItemIndisponivelDuasVezes(){ Usuario u=new Usuario("Guilherme"); Livro l=new Livro("Dom Casmurro",EstadoItem.USADO,u,"Machado"); l.marcarIndisponivel(); assertThrows(IllegalStateException.class,l::marcarIndisponivel); }
}

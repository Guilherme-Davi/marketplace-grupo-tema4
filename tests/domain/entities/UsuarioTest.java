package domain.entities;
import domain.enums.EstadoItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class UsuarioTest {
 @Test void naoDeveCriarUsuarioSemNome(){ assertThrows(IllegalArgumentException.class,()->new Usuario("")); }
 @Test void deveAdicionarCreditosAoUsuario(){ Usuario u=new Usuario("Guilherme"); u.adicionarCreditos(10); assertEquals(10,u.getCredito().getValor()); }
 @Test void naoDevePermitirSaldoNegativo(){ Usuario u=new Usuario("Guilherme"); assertThrows(IllegalArgumentException.class,()->u.removerCreditos(1)); }
 @Test void deveAdicionarItemDoProprioUsuario(){ Usuario u=new Usuario("Guilherme"); Livro l=new Livro("Clean Code",EstadoItem.USADO,u,"Robert C. Martin"); u.adicionarItem(l); assertEquals(1,u.getItens().size()); }
 @Test void naoDeveAdicionarItemDeOutroUsuario(){ Usuario g=new Usuario("Guilherme"); Usuario m=new Usuario("Maria"); Livro l=new Livro("Clean Code",EstadoItem.USADO,m,"Robert C. Martin"); assertThrows(IllegalArgumentException.class,()->g.adicionarItem(l)); }
}

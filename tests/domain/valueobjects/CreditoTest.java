package domain.valueobjects;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CreditoTest {
 @Test void naoDevePermitirCreditoNegativo(){ assertThrows(IllegalArgumentException.class,()->new Credito(-1)); }
 @Test void deveAdicionarCreditos(){ assertEquals(15,new Credito(10).adicionar(5).getValor()); }
 @Test void naoDeveAdicionarQuantidadeNegativa(){ assertThrows(IllegalArgumentException.class,()->new Credito(10).adicionar(-1)); }
 @Test void deveRemoverCreditos(){ assertEquals(6,new Credito(10).remover(4).getValor()); }
 @Test void naoDevePermitirRemoverMaisQueOSaldo(){ assertThrows(IllegalArgumentException.class,()->new Credito(3).remover(4)); }
 @Test void naoDeveRemoverQuantidadeNegativa(){ assertThrows(IllegalArgumentException.class,()->new Credito(10).remover(-1)); }
}

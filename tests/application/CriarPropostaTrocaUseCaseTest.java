package application;
import domain.entities.*;
import domain.enums.*;
import domain.services.ServicoDeMatch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CriarPropostaTrocaUseCaseTest {
 @Test void deveCriarPropostaQuandoMatchForPermitido(){ Usuario u1=new Usuario("G"); Usuario u2=new Usuario("M"); Livro l=new Livro("Livro",EstadoItem.USADO,u1,"Autor"); Eletronico e=new Eletronico("Fone",EstadoItem.SEMINOVO,u2,6); PropostaTroca p=new CriarPropostaTrocaUseCase(new ServicoDeMatch()).executar(l,e); assertEquals(StatusProposta.PENDENTE,p.getStatus()); }
 @Test void naoDeveCriarPropostaQuandoMatchNaoForPermitido(){ Usuario u=new Usuario("G"); Livro l1=new Livro("Livro",EstadoItem.USADO,u,"Autor"); Livro l2=new Livro("Outro",EstadoItem.USADO,u,"Autor"); CriarPropostaTrocaUseCase uc=new CriarPropostaTrocaUseCase(new ServicoDeMatch()); assertThrows(IllegalArgumentException.class,()->uc.executar(l1,l2)); }
}

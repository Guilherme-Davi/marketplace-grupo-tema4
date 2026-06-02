package domain.services;
import domain.entities.*;
import domain.enums.EstadoItem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ServicoDeMatchTest {
 @Test void devePermitirMatchEntreUsuariosDiferentesComItensDisponiveis(){ Usuario u1=new Usuario("G"); Usuario u2=new Usuario("M"); assertTrue(new ServicoDeMatch().podeSugerirTroca(new Livro("Livro",EstadoItem.USADO,u1,"Autor"),new Eletronico("Fone",EstadoItem.SEMINOVO,u2,6))); }
 @Test void naoDevePermitirMatchComMesmoUsuario(){ Usuario u=new Usuario("G"); assertFalse(new ServicoDeMatch().podeSugerirTroca(new Livro("Livro",EstadoItem.USADO,u,"Autor"),new Livro("Outro",EstadoItem.USADO,u,"Autor"))); }
 @Test void naoDevePermitirMatchComItemIndisponivel(){ Usuario u1=new Usuario("G"); Usuario u2=new Usuario("M"); Livro l=new Livro("Livro",EstadoItem.USADO,u1,"Autor"); Eletronico e=new Eletronico("Fone",EstadoItem.SEMINOVO,u2,6); l.marcarIndisponivel(); assertFalse(new ServicoDeMatch().podeSugerirTroca(l,e)); }
}

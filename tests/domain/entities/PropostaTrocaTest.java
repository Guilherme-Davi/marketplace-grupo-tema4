package domain.entities;
import domain.enums.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class PropostaTrocaTest {
 private Livro livro(Usuario u){ return new Livro("Clean Code",EstadoItem.USADO,u,"Robert C. Martin"); }
 private Eletronico fone(Usuario u){ return new Eletronico("Fone",EstadoItem.SEMINOVO,u,6); }
 @Test void propostaDeveComecarPendente(){ PropostaTroca p=new PropostaTroca(livro(new Usuario("G")),fone(new Usuario("M"))); assertEquals(StatusProposta.PENDENTE,p.getStatus()); }
 @Test void naoDevePermitirTrocaComMesmoUsuario(){ Usuario u=new Usuario("G"); assertThrows(IllegalArgumentException.class,()->new PropostaTroca(livro(u),new Livro("Outro",EstadoItem.USADO,u,"Autor"))); }
 @Test void deveAceitarPropostaEIndisponibilizarItens(){ Usuario u1=new Usuario("G"); Usuario u2=new Usuario("M"); Livro l=livro(u1); Eletronico f=fone(u2); PropostaTroca p=new PropostaTroca(l,f); p.aceitar(); assertEquals(StatusProposta.ACEITA,p.getStatus()); assertFalse(l.isDisponivel()); assertFalse(f.isDisponivel()); }
 @Test void deveRecusarProposta(){ PropostaTroca p=new PropostaTroca(livro(new Usuario("G")),fone(new Usuario("M"))); p.recusar(); assertEquals(StatusProposta.RECUSADA,p.getStatus()); }
 @Test void naoDeveAceitarPropostaRecusada(){ PropostaTroca p=new PropostaTroca(livro(new Usuario("G")),fone(new Usuario("M"))); p.recusar(); assertThrows(IllegalStateException.class,p::aceitar); }
 @Test void naoDeveRecusarPropostaAceita(){ PropostaTroca p=new PropostaTroca(livro(new Usuario("G")),fone(new Usuario("M"))); p.aceitar(); assertThrows(IllegalStateException.class,p::recusar); }
}

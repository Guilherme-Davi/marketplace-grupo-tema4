package infrastructure;

import domain.entities.Usuario;
import java.util.*;

public class UsuarioRepositoryInMemory {
    private final List<Usuario> usuarios = new ArrayList<>();

    public void salvar(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário não pode ser nulo");
        usuarios.add(usuario);
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarios.stream().filter(usuario -> usuario.getId().equals(id)).findFirst();
    }

    public Optional<Usuario> buscarPorIndice(int indice) {
        if (indice < 0 || indice >= usuarios.size()) return Optional.empty();
        return Optional.of(usuarios.get(indice));
    }

    public List<Usuario> listarTodos() { return List.copyOf(usuarios); }
}

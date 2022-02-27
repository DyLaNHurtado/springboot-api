package es.dylanhurtado.springbootapirestjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

// Indicamos que es uns servicio de detalles de usuario para Spring Security
@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " no encontrado"));
    }

    public UserDetails loadTrainerById(UUID trainerID) {
        return usuarioService.findUserById(trainerID)
                .orElseThrow(() -> new UsernameNotFoundException("Trainer con id: " + trainerID + " no encontrado"));
    }
}

package com.ticketera.av.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ticketera.av.model.Usuario;

@Transactional
public interface UsuarioRepositoy extends JpaRepository<Usuario,Long> {

    public Usuario save(Usuario usuario);
    public List<Usuario> findAll();
    public Optional<Usuario> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("update Usuario c SET c.password = :password, c.passwordProvisoria=0 WHERE c.id = :userId")
    void updateContraseña(@Param("userId") Long userId, @Param("password") String contraseña);
}

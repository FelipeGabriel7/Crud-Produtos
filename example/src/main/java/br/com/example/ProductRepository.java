package br.com.example;

import br.com.example.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Produto, Long> {
}

package org.lessons.springlamiapizzeriacrud.repository;

import org.lessons.springlamiapizzeriacrud.model.Ingridient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngridientRepository extends JpaRepository<Ingridient, Integer> {
}

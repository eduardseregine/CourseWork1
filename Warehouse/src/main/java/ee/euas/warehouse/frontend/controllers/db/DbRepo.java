package ee.euas.warehouse.frontend.controllers.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DbRepo extends JpaRepository <Employees, Integer> {

}



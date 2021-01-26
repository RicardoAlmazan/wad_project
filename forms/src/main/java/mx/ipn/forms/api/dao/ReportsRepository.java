package mx.ipn.forms.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.ipn.forms.api.model.Report;

public interface ReportsRepository extends JpaRepository<Report, Integer> {
  
}

package com.philately.repository;

import com.philately.model.entity.Paper;
import com.philately.model.enums.PaperType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
    Paper findByPaperName(PaperType paperType);
}

package org.knullci.knull.domain.repository;

import org.knullci.knull.domain.model.Build;

import java.util.List;
import java.util.Optional;

public interface BuildRepository {

    Build saveBuild(Build build);

    Optional<Build> findById(Long id);

    List<Build> findByJobId(Long jobId);

    List<Build> findAll();

    /**
     * Find all builds with pagination, sorted by ID descending (newest first)
     * 
     * @param page Page number (0-indexed)
     * @param size Number of items per page
     * @return List of builds for the requested page
     */
    List<Build> findAllPaginated(int page, int size);

    /**
     * Get total count of all builds
     * 
     * @return Total number of builds
     */
    long countAll();

    void updateBuild(Build build);

}

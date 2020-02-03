package epam.rd.traydakalo.repository;

import epam.rd.traydakalo.entity.Claim;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClaimRepository extends PagingAndSortingRepository<Claim, Long> {
}

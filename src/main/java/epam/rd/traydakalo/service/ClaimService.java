package epam.rd.traydakalo.service;

import epam.rd.traydakalo.entity.Claim;
import epam.rd.traydakalo.exceptions.NoSuchClaimException;
import epam.rd.traydakalo.repository.ClaimRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartResolver;

import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;

    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    public Claim save(Claim claim) {
        return claimRepository.save(claim);
    }

    public Claim findById(Long id) {
        return claimRepository.findById(id).orElseThrow(NoSuchClaimException::new);
    }

    public Page<Claim> findAll(Pageable pageable){
        return claimRepository.findAll(pageable);
    }

    public Claim updateClaim(Claim claim) {
        if (!claimRepository.existsById(claim.getId())) {
            throw new NoSuchClaimException();
        }
        return claimRepository.save(claim);
    }

    public void deleteClaim(Claim claim){
        claimRepository.delete(claim);
    }
}

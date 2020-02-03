package epam.rd.traydakalo.controllers;

import epam.rd.traydakalo.entity.Claim;
import epam.rd.traydakalo.service.ClaimService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/claims/{id}")
    public Claim getClaim(@PathVariable Long id) {
        return claimService.findById(id);
    }

    @GetMapping(value = "/claims", params = {"page", "size"})
    public Page<Claim> getClaimsPage(Pageable pageable) {
        return claimService.findAll(pageable);
    }

    @PostMapping("/claim")
    public Claim saveClaim(@RequestBody Claim claim) {
        return claimService.save(claim);
    }

    @PutMapping("/claim")
    public Claim updateClaim(@RequestBody Claim claim) {
        return claimService.updateClaim(claim);
    }

    @DeleteMapping("/claim")
    public void deleteClaim(@RequestBody Claim claim) {
        claimService.deleteClaim(claim);
    }
}

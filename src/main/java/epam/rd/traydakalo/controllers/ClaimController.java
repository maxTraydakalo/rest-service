package epam.rd.traydakalo.controllers;

import epam.rd.traydakalo.entity.Claim;
import epam.rd.traydakalo.service.ClaimService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClaimController {
    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/claim/{id}")
    public Claim getClaim(@PathVariable Long id){
        return claimService.findById(id);
    }

    @PostMapping("/claim")
    public Claim saveClaim(@RequestBody Claim claim){
        return claimService.save(claim);
    }

    @PutMapping("/claim")
    public Claim updateClaim(@RequestBody Claim claim){
        return claimService.updateClaim(claim);
    }

    @DeleteMapping("/claim")
    public void deleteClaim(@RequestBody Claim claim){
        claimService.deleteClaim(claim);
    }
}

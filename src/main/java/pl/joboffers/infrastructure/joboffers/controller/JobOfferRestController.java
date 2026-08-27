package pl.joboffers.infrastructure.joboffers.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joboffers.domain.joboffers.JobOfferFacade;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;
import pl.joboffers.infrastructure.joboffers.controller.dto.JobOfferRequestDto;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
@RequestMapping("/offers")
public class JobOfferRestController {

    private final JobOfferFacade jobOfferFacade;

    @PostMapping
    public ResponseEntity<JobOfferDto> addNewJobOffer(@RequestBody JobOfferRequestDto jobOfferRequestDto){
        JobOfferDto jobOfferDto = jobOfferFacade.saveNewJobOffer(jobOfferRequestDto);
        log.info("New JobOffer: "+ jobOfferDto.title() + " with URL: "+ jobOfferRequestDto.offerUrl() + " saved");
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOfferDto);
    }

    @GetMapping
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers(){
        List<JobOfferDto> allJobsOffers = jobOfferFacade.findAllJobsOffers();
        return ResponseEntity.ok(allJobsOffers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOfferDto> findJobOfferById(@PathVariable String id){
        JobOfferDto jobOfferById = jobOfferFacade.findJobOfferById(id);
        return ResponseEntity.ok(jobOfferById);
    }
}

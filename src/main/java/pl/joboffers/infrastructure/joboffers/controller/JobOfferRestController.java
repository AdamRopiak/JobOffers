package pl.joboffers.infrastructure.joboffers.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joboffers.domain.joboffers.JobOfferFacade;
import pl.joboffers.domain.joboffers.dto.JobOfferDto;

import java.util.List;

@RestController
@Log4j2
@AllArgsConstructor
public class JobOfferRestController {

    private final JobOfferFacade jobOfferFacade;

    @PostMapping("/offers")
    public ResponseEntity<JobOfferDto> addNewJobOffer(@RequestBody JobOfferRequestDto jobOfferRequestDto){
        JobOfferDto jobOfferDto = jobOfferFacade.saveNewJobOffer(jobOfferRequestDto);
        log.info("New JobOffer: "+ jobOfferDto.title() + " with URL: "+ jobOfferRequestDto.offerUrl() + " saved");
        return ResponseEntity.ok(jobOfferDto);
    }

    @GetMapping("/offers")
    public ResponseEntity<List<JobOfferDto>> getAllJobOffers(){
        List<JobOfferDto> allJobsOffers = jobOfferFacade.findAllJobsOffers();
        return ResponseEntity.ok(allJobsOffers);
    }
}

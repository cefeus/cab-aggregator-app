package org.passengerservice.controller.api;

import com.modsen.passengerservice.dto.request.PassengerRegistrationRequest;
import com.modsen.passengerservice.dto.request.PassengerRequest;
import com.modsen.passengerservice.dto.request.PhoneUpdateRequest;
import com.modsen.passengerservice.dto.response.PassengerResponse;
import com.modsen.passengerservice.dto.response.PassengersPagedResponse;
import com.modsen.passengerservice.dto.response.RegisteredPassengerResponse;
import com.modsen.passengerservice.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.modsen.passengerservice.util.ApiRoutesConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PASSENGERS_API_V1)
public class PassengerController {

    private final PassengerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegisteredPassengerResponse createPassenger(@RequestBody PassengerRegistrationRequest dto) {
        return service.register(dto);
    }

    @PutMapping(UPDATE_ENDPOINT)
    public PassengerResponse updatePassenger(@PathVariable Long id, @RequestBody PassengerRequest dto) {
        return service.update(id, dto);
    }

    @PutMapping(UPDATE_PHONE_ENDPOINT)
    public PassengerResponse updatePassengerPhone(@PathVariable Long id, @RequestBody PhoneUpdateRequest dto) {
        return service.updatePhone(id, dto);
    }

    @GetMapping
    public PassengersPagedResponse findAllActive(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return service.getAllActive(pageable);
    }

    @GetMapping(FIND_ALL_ENDPOINT)
    public PassengersPagedResponse findAll(@PageableDefault(
            size = 20,
            sort = "createdAt",
            direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return service.getAll(pageable);
    }

    @GetMapping(FIND_BY_ID_ENDPOINT)
    public  PassengerResponse findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping(DELETE_ENDPOINT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")Long id) {
        service.delete(id);
    }

    @PatchMapping(SET_PAYMENT_CASH_ENDPOINT)
    public void setPaymentType(@PathVariable("id")Long id) {
        service.setPaymentTypeCash(id);
    }

    @PatchMapping(SET_PAYMENT_CARD_ENDPOINT)
    public void setPaymentTypeCard(@PathVariable("id")Long id) {
        service.setPaymentTypeCard(id);
    }
}

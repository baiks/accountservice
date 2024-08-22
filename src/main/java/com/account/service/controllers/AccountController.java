package com.account.service.controllers;


import com.account.service.dtos.AccountsDto;
import com.account.service.entities.Accounts;
import com.account.service.services.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Tag(name = "ACCOUNT", description = "Handles the management of bank accounts, including account creation, balance inquiries, and account closure.")
public class AccountController {


    private final AccountService accountService;

    @RequestMapping(method = RequestMethod.GET, value = "/ping")
    public String ping() {
        return "Ping successful!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create/{customerId}")
    @Operation(summary = "Create an account", description = "Returns the details of the account created.\n" + "\n" + "Example Requests: /create/1")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "POST: /create/{customerId}")})
    public ResponseEntity<Accounts> create(@Valid @PathVariable Long customerId) {
        return accountService.create(customerId);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/view/{id}")
    @Operation(summary = "find account by Id", description = "Returns the account details for the account being searched.\n" + "\n" + "Example Requests:\n" + "\n" + "/view/1")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "GET: /view/{id}")})
    public ResponseEntity<Accounts> findById(@Valid @PathVariable Long id) {
        return accountService.findById(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/close/{id}")
    @Operation(summary = "Close account", description = "Returns the details of the closed account.\n" + "\n" + "Example Requests:\n")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /close/1")})
    public ResponseEntity<Accounts> close(@Valid @PathVariable Long id) {
        return accountService.close(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/activate/{id}")
    @Operation(summary = "Activate account", description = "Returns the details of the activated account.\n" + "\n" + "Example Requests:\n")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /activate/1")})
    public ResponseEntity<Accounts> activate(@Valid @PathVariable Long id) {
        return accountService.activate(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/deactivate/{id}")
    @Operation(summary = "De Activate", description = "Returns the details of the de activated.\n" + "\n" + "Example Requests:\n")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "PATCH: /deactivate/1")})
    public ResponseEntity<Accounts> deActivate(@Valid @PathVariable Long id) {
        return accountService.deActivate(id);
    }
}
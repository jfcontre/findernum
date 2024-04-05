package com.finder.adapters.in.http.finder;

import com.finder.application.ports.in.FindMaxNumberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
@Tag(name = FindMaxNumberController.CONTEXT, description = "Number lookup operations with remainder")
public class FindMaxNumberController {
    static final String CONTEXT = "/api";
    private final FindMaxNumberUseCase findMaxNumberUseCase;

    @Autowired
    public FindMaxNumberController(FindMaxNumberUseCase findMaxNumberUseCase) {
        this.findMaxNumberUseCase = findMaxNumberUseCase;
    }

    @GetMapping("/findMaxK")
    @Operation(summary = "return number found")
    public ResponseEntity<ResponseDto> findMaxK(@RequestParam int x, @RequestParam int y, @RequestParam int n) {
        return commonResponse(x,y,n);
    }

    @PostMapping("/findMaxK")
    @Operation(summary = "return number found")
    public ResponseEntity<ResponseDto> findMaxK(@RequestBody RequestDto requestBody) {
        return commonResponse(requestBody.getX(), requestBody.getY(), requestBody.getN());
    }

    private ResponseEntity<ResponseDto> commonResponse(int x,int y, int n){
        int result = findMaxNumberUseCase.findMaxNumber(x, y, n);
        ResponseDto response = new ResponseDto("ok", result);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
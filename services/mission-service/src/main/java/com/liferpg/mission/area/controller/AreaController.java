package com.liferpg.mission.area.controller;


import com.liferpg.mission.area.dto.AreaResponse;
import com.liferpg.mission.area.dto.CreateAreaRequest;
import com.liferpg.mission.area.dto.UpdateAreaRequest;
import com.liferpg.mission.area.entity.Area;
import com.liferpg.mission.area.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    private final AreaService areaService;

    public AreaController(AreaService areaService){
        this.areaService = areaService;
    }

    @PostMapping
    public ResponseEntity<AreaResponse> createArea (@Valid @RequestBody CreateAreaRequest request){
        AreaResponse response = areaService.createArea(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<AreaResponse> findAreaById(@PathVariable Long id){
      AreaResponse response =  areaService.findAreaById(id);
      return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AreaResponse>> findAreaAll(){
        List<AreaResponse> listado = areaService.findAreaAll();

        return ResponseEntity.ok(listado);
    }

    @PutMapping("{id}")
    public ResponseEntity<AreaResponse> updateArea(@PathVariable Long id,  @Valid @RequestBody UpdateAreaRequest request){
      AreaResponse areaResponse =  areaService.updateArea(id, request);
      return ResponseEntity.ok(areaResponse);
    }

}

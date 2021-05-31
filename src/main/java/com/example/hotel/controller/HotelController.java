package com.example.hotel.controller;

import com.example.hotel.model.Hotel;
import com.example.hotel.search.HotelSpecificationsBuilder;
import com.example.hotel.search.SearchOperation;
import com.example.hotel.service.HotelService;
import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(path = "api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<String> addHotel(@RequestBody Hotel hotel){
        hotelService.addHotel(hotel);
        return new ResponseEntity<>("Hotel Added Successfully.", HttpStatus.ACCEPTED);
    }

    @PutMapping
    public ResponseEntity<String> updateHotel(@RequestParam("id") Long id, @RequestBody Hotel hotel){
        hotelService.updateHotel(id, hotel);
        return new ResponseEntity<>("Hotel Updated Successfully.", HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteHotel(@RequestParam("id") Long id){
        hotelService.deleteHotel(id);
        return new ResponseEntity<>("Hotel Deleted Successfully.", HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<Hotel> getHotels(){
        List<Hotel> hotels = hotelService.getHotels();
        return hotels;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find")
    @ResponseBody
    public List<Hotel> findAllBySpecification(@RequestParam(value = "search") String search) {
        HotelSpecificationsBuilder builder = new HotelSpecificationsBuilder();
        String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        Specification<Hotel> spec = builder.build();
        return hotelService.findAll(spec);
    }

}

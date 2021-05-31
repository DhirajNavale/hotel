package com.example.hotel.service;

import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Data
public class HotelService {

    private final HotelRepository hotelRepository;

    @Transactional
    public void addHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Transactional
    public void updateHotel(Long id, Hotel hotel) {
        hotelRepository.findById(id).ifPresentOrElse((e) ->{
                    hotelRepository.save(hotel);
                },
                () -> {
            throw new IllegalArgumentException("Sorry hotel does not exist.");
        });
    }

    public void deleteHotel(Long id) {

        hotelRepository.findById(id).ifPresentOrElse((e) ->{
            hotelRepository.deleteById(id);
        }, ()->{
                    throw new IllegalArgumentException("Sorry hotel does not exist.");
        });
    }

//    @Transactional
    public List<Hotel> getHotels() {
        return  hotelRepository.findAll();
    }

    public List<Hotel> findAll(Specification<Hotel> spec) {
        return hotelRepository.findAll(Specification.where(spec), Sort.by("stars").descending());
    }

    public Hotel getHotel(Long id){
        if(!hotelRepository.findById(id).isPresent())
            throw new IllegalArgumentException("Hotel does not exist");

        return hotelRepository.findById(id).get();
    }

    public void updateRating(Long hotelId, Float stars) {
        Hotel hotel = hotelRepository.findById(hotelId).get();
        if(hotel.getStars() == null)
            hotel.setStars(stars);
        else
            hotel.setStars((hotel.getStars() + stars)/2);
        hotelRepository.save(hotel);
    }
}

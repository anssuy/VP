package mk.ukim.finki.wp.lab.repository.mock.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.mock.BookReservationRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryBookReservationRepository implements BookReservationRepository {
    @Override
    public BookReservation save(BookReservation reservation) {
        DataHolder.reservations.add(reservation);
        return reservation;
    }
}

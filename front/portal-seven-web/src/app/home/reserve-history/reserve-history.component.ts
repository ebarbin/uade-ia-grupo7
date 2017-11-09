import { ReservationService } from './services/reservation.service';
import { Reservation } from './models/reservation.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reserve-history',
  templateUrl: './reserve-history.component.html',
  styleUrls: ['./reserve-history.component.css']
})
export class ReserveHistoryComponent implements OnInit {

  constructor(private reservationService: ReservationService) { }

  reservations:Reservation[] = [];

  ngOnInit() {
    this.reservationService.getReservations().then((reservations:Reservation[])=>{
      this.reservations = reservations;
    })
  }

}

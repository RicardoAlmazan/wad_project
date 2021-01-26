import { Component, OnInit } from '@angular/core';
import { StorageutilService } from '../services/storageutil.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  solicitudesCount = 0;

  constructor(private storageUtilService: StorageutilService) { }

  ngOnInit() {
    this.updatesolicitudesCount();
  }

  updatesolicitudesCount() {
    this.solicitudesCount = 12;
  }


}

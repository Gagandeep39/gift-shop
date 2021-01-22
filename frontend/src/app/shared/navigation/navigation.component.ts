import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {

  isLoggedIn;
  loginSubscription;
  activeTabs;
  userTabs = [
    { name: 'About', link: 'about' },
    { name: 'Cart', link: 'about' },
    { name: 'My Purchase', link: 'about' },
  ];
  anonymousTab = [
    { name: 'About', link: 'about' },
    { name: 'Login', link: '/login' },
    { name: 'Register', link: '/register' },
  ];
  adminTabs= [
    { name: 'add', link: 'add' },
    { name: 'view', link: 'view' },
  ];;

  constructor(
    private authService: AuthService,
    private eventService: EventService
  ) {
    this.initializeNavItems();
  }
  initializeNavItems() {
    this.eventService.loggedInUser.subscribe(res => {
      if (res ===null) {
        this.isLoggedIn = false;
        this.activeTabs = this.anonymousTab;
      }else if (this.isLoggedIn?.role === 'Admin') {
        this.isLoggedIn = true;
        this.activeTabs = this.adminTabs;
      } else {
        this.isLoggedIn = true;
        this.activeTabs = this.userTabs;
      }
      
    })
  }

  ngOnInit(): void {}

  logOut() {
    this.authService.logout();
  }
}

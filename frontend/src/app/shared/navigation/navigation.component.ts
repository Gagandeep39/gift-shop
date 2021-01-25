import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/models/category.model';
import { AuthService } from 'src/app/services/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { EventService } from 'src/app/services/event.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent implements OnInit {
  isLoggedIn: boolean;
  activeTabs = [];
  userTabs = [
    { name: 'About', link: '/about' },
    { name: 'Cart', link: '/products/cart' },
    { name: 'My Purchase', link: '/products/orders' },
  ];
  anonymousTab = [
    { name: 'About', link: '/about' },
    { name: 'Login', link: '/login' },
    { name: 'Register', link: '/register' },
  ];
  adminTabs = [
    { name: 'About', link: '/about' },
    { name: 'Add', link: '/admin/add' },
    { name: 'View', link: '/admin/view' },
  ];
  categories: Category[] = [];

  constructor(
    private authService: AuthService,
    private eventService: EventService,
    private categoryService: CategoryService,
  ) {
    this.initializeApp();
    this.initializeNavItems();
  }
  initializeApp() {
    // If user data is present and token not expired, emi event to update navi
    if (this.authService.isAuthenticated()) {
      this.eventService.loggedInUser.next(
        this.authService.fetchFromSessionStorage()
      );
    }
    // Log out user if toke already expired
    else this.authService.logout();
    this.initCategories();
  }
  initCategories() {
    this.categoryService.fetchAllCategories().subscribe((res: Category[]) => {
      this.categories = res;
    })
  }

  initializeNavItems() {
    this.eventService.loggedInUser.subscribe((res) => {
      if (res === null) {
        this.isLoggedIn = false;
        this.activeTabs = this.anonymousTab;
      } else if (res?.role === 'Admin') {
        this.isLoggedIn = true;
        this.activeTabs = this.adminTabs;
      } else {
        this.isLoggedIn = true;
        this.activeTabs = this.userTabs;
      }
    });
  }

  ngOnInit(): void {}

  logOut() {
    this.authService.logout();
  }

  changeCategory(category: Category) {
    this.eventService.categoryChanged.next(category);
  }
}

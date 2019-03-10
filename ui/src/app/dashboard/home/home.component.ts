import { Component, OnInit, Input, ChangeDetectionStrategy } from '@angular/core';

@Component({
  selector: 'app-home',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title = 'Angular Scala Play';
  @Input() position = 'normal';

  menuItems = [
    {
      title: 'User Account',
      expanded: true,
      icon: 'nb-person',
      children: [
        {
          title: 'Login',
          link: ['/auth/login'], // goes into angular `routerLink`
        },
        {
          title: 'Logout',
          link: ['/auth/logout'],
        },
        {
          title: 'Register',
          link: ['/auth/register'],
        },
        {
          title: 'Request Password',
          link: ['/auth/request-password'],
        },
        {
          title: 'Reset Password',
          link: ['/auth/reset-password'],
        },
      ],
    }
  ];

  constructor() { }

  ngOnInit() {
  }

}

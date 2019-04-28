import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NbSidebarModule, NbLayoutModule, NbSidebarService, NbActionsModule, NbMenuModule } from '@nebular/theme';
import { NbPasswordAuthStrategy, NbAuthModule, NbDummyAuthStrategy } from '@nebular/auth';
import { HomeComponent } from './home/home.component';
import { DashboardRoutingModule } from './dashboard-routing.module';

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    NbLayoutModule,
    NbActionsModule,
    NbMenuModule.forRoot(),
    NbAuthModule.forRoot({
      strategies: [
        NbPasswordAuthStrategy.setup({
          name: 'email',
          baseEndpoint: 'http://localhost:9000/api',
          login: {
            // ...
            endpoint: '/user',
          },
          register: {
            // ...
            endpoint: '/api/auth/register',
          },
        })
        // NbDummyAuthStrategy.setup({
        //   name: 'email',
        //   delay: 2500,
        // })
      ],
      forms: {
        register: {
          redirectDelay: 500,
          strategy: 'email',
          showMessages: {
            success: true,
            error: true,
          },
          terms: true,
          socialLinks: [],
        },
      },
    }),
    NbSidebarModule,
    DashboardRoutingModule,
  ],
  exports: [
  ],
  providers: [NbSidebarService]
})
export class DashboardModule { }

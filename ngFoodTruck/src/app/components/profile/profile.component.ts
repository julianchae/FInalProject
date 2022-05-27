import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { ProfileService } from 'src/app/services/profile.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  color1 = '#7F6C79';
  // private url = environment.baseUrl + "api/profile";

  //newProfile: Profile = new Profile();
  tempUser: User = new User();
  user: User | null = null;

  constructor(private profileService: ProfileService, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
        this.tempUser = Object.assign({},user);
        this.tempUser.password = '';
         this.user = user;
        console.log(user);
        // user1 = user;
        // return user;
      },
      error:(fail) => {
        console.error(fail);
        // return null;
      }
    });
  }

  updateUser(user: User, id: number | null){
    if (!id) {
      return
    }
    this.profileService.update(user, id).subscribe(
      data => {
        //this.reload();
        //this.editProfile = null;
        Object.assign({}, user);
      },
      err => console.error(err)
    );

  }
}

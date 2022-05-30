import { Comment } from './../../models/comment';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FoodTruck } from 'src/app/models/food-truck';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() truck: FoodTruck = new FoodTruck();
 comments : Comment [] = [];
  newComment: Comment = new Comment();
  user: User | null = null;
  comment: Comment = new Comment();

  constructor(private commentSvc: CommentService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService) { }

  ngOnInit(): void {
    this.loadCommentsForTruck();
    this.authService.getLoggedInUser().subscribe({
      next:(user) => {
        this.user = Object.assign({},user);
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

  loadCommentsForTruck(){
    this.commentSvc.getCommentsOnTruck(this.truck.id).subscribe(
      success => this.comments = success,
      err => console.log("Observable got an error " + err)
    );
  }

  createCommentOnTruck(){
this.commentSvc.createCommentOnTruck(this.truck.id, this.comment).subscribe(
  data => {
    this.loadCommentsForTruck();
    this.newComment = new Comment();
  },
  err => console.log("Observable got an error " + err)
  );



  }
}

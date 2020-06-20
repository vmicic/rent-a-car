import { Component, OnInit } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { AuthService } from 'src/app/shared/auth/auth.service';
import { Subject, Observable, Subscription } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { interval } from 'rxjs'

@Component({
  selector: 'app-show-all',
  templateUrl: './show-all.component.html',
  styleUrls: ['./show-all.component.css']
})
export class ShowAllComponent implements OnInit {

  messages: any[];
  messagesObs$: Subject<any[]> = new Subject<any[]>();

  messagesToShow: any[] = [];
  messagesToShowObs$: Subject<any[]> = new Subject<any[]>();
  
  uniqueChatters: String[];
  uniqueChattersObs$: Subject<String[]> = new Subject<String[]>();

  username: string;

  usernameSelected: any;

  sub: Subscription;

  newMessageForm: FormGroup;

  constructor(
    private messageService: MessageService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.newMessageForm = this.formBuilder.group({
      usernameReceiver: ['', Validators.required],
      content: ['', Validators.required]
    });

    this.username = this.authService.getUsername();

    this.messageService.getAll().subscribe(
      response => {
        this.messages = response.body;
        this.messagesObs$.next(this.messages);

        this.uniqueChatters = this.getUniqueChatters(this.messages);
        this.uniqueChattersObs$.next(this.uniqueChatters);

        //console.log("All messages");
        //console.log(this.messages);
      }
    )

    this.sub = interval(5000).subscribe(
      val => {
        this.messageService.getAll().subscribe(
          response => {
            this.messages = response.body;
            this.messagesObs$.next(this.messages);
    
            this.uniqueChatters = this.getUniqueChatters(this.messages);
            this.uniqueChattersObs$.next(this.uniqueChatters);
    
            //console.log("All messages");
            //console.log(this.messages);
          }
        )
        this.messagesToShow = [];

        for (let i = 0; i < this.messages.length; i++) {
    
          if (this.messages[i].receiver.email === this.usernameSelected) {
            this.messagesToShow.push(this.messages[i]);
          }
    
          if (this.messages[i].sender.email === this.usernameSelected) {
            this.messagesToShow.push(this.messages[i]);
          }
        }
    
        //console.log("mesasges to show");
        //console.log(this.messagesToShow);
        this.messagesToShowObs$.next(this.messagesToShow);
      }
    )
  }

  getUniqueChatters(messages: any[]) {

    let users: String[] = [];

    for(let i = 0; i < messages.length; i++) {
      let userExists: boolean = false;

      if(messages[i].sender.email != this.username) {
        for(let j = 0; j < users.length; j++) {
          if(users[j] === messages[i].sender.email) {
            userExists = true;
            break;
          }
        }

        if(!userExists) {
          users.push(messages[i].sender.email);
        }
      }

      if(messages[i].receiver.email != this.username) {
        for(let j = 0; j < users.length; j++) {
          if(users[j] === messages[i].receiver.email) {
            userExists = true;
            break;
          }
        }

        if(!userExists) {
          users.push(messages[i].receiver.email);
        }
      }
    }

    return users;
  }

  selectChat(event) {
    event = event || window.event; // IE
    var target = event.target || event.srcElement; // IE

    this.usernameSelected = target.id;

    this.newMessageForm.controls["usernameReceiver"].setValue(this.usernameSelected);

    this.messagesToShow = [];

    for (let i = 0; i < this.messages.length; i++) {

      if (this.messages[i].receiver.email === this.usernameSelected) {
        this.messagesToShow.push(this.messages[i]);
      }

      if (this.messages[i].sender.email === this.usernameSelected) {
        this.messagesToShow.push(this.messages[i]);
      }
    }

    //console.log("mesasges to show");
    //console.log(this.messagesToShow);
    this.messagesToShowObs$.next(this.messagesToShow);
    return false;
  }

  sendMessage() {

    this.messageService.sendMessage(this.newMessageForm.value).subscribe(
      response => {
        this.newMessageForm.controls["content"].setValue("");
        //refresh so he can see his sent message
        this.messageService.getAll().subscribe(
          response => {
            this.messages = response.body;
            this.messagesObs$.next(this.messages);
    
            this.uniqueChatters = this.getUniqueChatters(this.messages);
            this.uniqueChattersObs$.next(this.uniqueChatters);

            this.messagesToShow = [];

            for (let i = 0; i < this.messages.length; i++) {
        
              if (this.messages[i].receiver.email === this.usernameSelected) {
                this.messagesToShow.push(this.messages[i]);
              }
        
              if (this.messages[i].sender.email === this.usernameSelected) {
                this.messagesToShow.push(this.messages[i]);
              }
            }
        
            //console.log("mesasges to show");
            //console.log(this.messagesToShow);
            this.messagesToShowObs$.next(this.messagesToShow);
          }
        )
      }
    )
  }

}

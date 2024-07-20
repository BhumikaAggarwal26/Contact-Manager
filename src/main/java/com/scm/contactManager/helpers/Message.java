package com.scm.contactManager.helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    
    private String content;
    
    private MessageType type = MessageType.green; //default set to GREEN
}

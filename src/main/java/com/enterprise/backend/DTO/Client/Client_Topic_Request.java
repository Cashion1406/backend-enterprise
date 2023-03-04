package com.enterprise.backend.DTO.Client;

import com.enterprise.backend.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client_Topic_Request {

   private long topic_id;

   private String client_id;
}

package com.enterprise.backend.response;


import lombok.*;

@Data
@NoArgsConstructor
@Getter
@Setter
public class FollowTopic {

    private String client_role;


    private String client_id;

    private String topic_name;
    private String image_url;
    private long topic_id;


}

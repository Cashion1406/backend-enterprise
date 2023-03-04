package com.enterprise.backend.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientReaction {
    private Long idea_id;

    private Boolean reaction;
}

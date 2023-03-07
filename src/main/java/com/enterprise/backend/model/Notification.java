package com.enterprise.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "noti_content")
    private String content;

    @Column(name = "client_id")
    private String client_id;

    @Column(name = "noti_time")
    private String createAt;

    @Column(name = "noti_status")
    private Boolean status;

    @Column(name = "isDeleted")
    private Boolean idDelete;
}

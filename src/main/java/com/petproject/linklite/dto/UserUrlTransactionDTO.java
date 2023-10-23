package com.petproject.linklite.dto;

import com.petproject.linklite.entity.Url;

import lombok.Data;

@Data
public class UserUrlTransactionDTO {
    private long userId;
    private Url url;
}

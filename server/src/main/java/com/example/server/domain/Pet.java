package com.example.server.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pet   {
  private Integer id;
  private String name;
}

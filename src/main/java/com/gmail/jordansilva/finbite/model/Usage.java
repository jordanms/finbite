package com.gmail.jordansilva.finbite.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Usage {

  @NonNull
  int callInMinutes;

  @NonNull
  int sms;
}
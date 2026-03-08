package com.example.service.useractivity.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ActivityTypeTest {

  @Test
  void testEnumValues() {
    ActivityType[] values = ActivityType.values();
    assertEquals(6, values.length);
    assertEquals(ActivityType.LOGIN, ActivityType.valueOf("LOGIN"));
    assertEquals(ActivityType.LOGOUT, ActivityType.valueOf("LOGOUT"));
    assertEquals(ActivityType.PAGE_VIEW, ActivityType.valueOf("PAGE_VIEW"));
    assertEquals(ActivityType.CLICK, ActivityType.valueOf("CLICK"));
    assertEquals(ActivityType.FORM_SUBMIT, ActivityType.valueOf("FORM_SUBMIT"));
    assertEquals(ActivityType.DOWNLOAD, ActivityType.valueOf("DOWNLOAD"));
  }
}

package com.example.service.useractivity.model;

/** Represents the type of user activity. */
public enum ActivityType {
  /** User authentication event. */
  LOGIN,

  /** User sign out event. */
  LOGOUT,

  /** Page navigation event. */
  PAGE_VIEW,

  /** UI interaction event. */
  CLICK,

  /** Form submission event. */
  FORM_SUBMIT,

  /** File download event. */
  DOWNLOAD
}

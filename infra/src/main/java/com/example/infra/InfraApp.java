package com.example.infra;

import software.amazon.awscdk.App;

/** Main AWS CDK application. */
public final class InfraApp {

  private InfraApp() {
    // Utility class
  }

  public static void main(String[] args) {
    App app = new App();
    new MainStack(app, "MainStack", null);
    app.synth();
  }
}
